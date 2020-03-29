package com.sprboot.plugin.resttempex.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.sprboot.plugin.resttempex.bean.param.HeaderParams;
import com.sprboot.plugin.resttempex.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.util.IOUtils;

/**
 * 编  号：
 * 名  称：RestUtils
 * 描  述：
 * 下载：参考：https://my.oschina.net/heweipo/blog/683233
 * 完成日期：2020/3/29 21:57
 * @author：felix.shao
 */
public class RestUtils {

	private static Logger logger = LoggerFactory.getLogger(RestUtils.class);
	
	/**
	 * 下载文件到本地
	 * @param url http文件资源url
	 * @param folder 本地文件夹路径
	 * @param fileName 存储文件名
	 * @param headerParams http请求头参数
	 * @throws IllegalArgumentException
	 * @throws ServiceException
	 */
	public static void downFile(String url, String folder, String fileName, HeaderParams headerParams)
    		throws IllegalArgumentException, ServiceException {
		downFile(url, folder + "/" + fileName, headerParams);
    }
	
	/**
	 * 下载文件到本地
	 * @param url http文件资源url
	 * @param filePath 本地文件路径
	 * @param headerParams http请求头参数
	 * @throws IllegalArgumentException
	 * @throws ServiceException
	 */
	public static void downFile(String url, String filePath, HeaderParams headerParams)
			throws IllegalArgumentException, ServiceException{
		byte[] data = downFileRequest(url, headerParams);
		
		File file = new File(filePath);
		
		if(!StringUtils.isEmpty(file.getParent())){
			/** 文件所在文件夹不存在,则先创建好父文件夹 */
			File fileFolder = new File(file.getParent());
			if(!fileFolder.exists()){
				logger.info("mkdirs:{}", file.getParent());
				fileFolder.mkdirs();
			}
		}
		
		FileOutputStream out = null;
		
		try {
			out = new FileOutputStream(file);
			out.write(data);
		} catch (IOException e) {
			logger.error("{}", e);
			throw new ServiceException(ServiceException.DEFAULT_FAIL_CODE, "IOException." + e.getMessage());
		} finally {
			IOUtils.close(out);
		}
	}
	
	/**
	 * 发送基本的get json请求头格式
	 * @param url
	 * @param cls
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ServiceException
	 */
	public static <T> T sendBaseGetJsonRequest(String url, Class<T> cls)
			throws IllegalArgumentException, ServiceException {
		HeaderParams headerParams = HeaderParams.Builder
				.contentType("application/json; charset=utf-8")
				.accept("application/json; charset=utf-8");
		return sendRequest(url, HttpMethod.GET, headerParams, null, cls);
	}
	
	/**
	 * 发送http请求
	 * @param url
	 * @param httpMethod
	 * @param headerParams
	 * @param cls
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ServiceException
	 */
	public static <T> T sendRequest(String url, HttpMethod httpMethod, HeaderParams headerParams, Class<T> cls)
			throws IllegalArgumentException, ServiceException {
		return sendRequest(url, httpMethod, headerParams, null, cls);
	}
	
	/**
	 * 发送http请求
	 * @param url
	 * @param httpMethod
	 * @param headerParams
	 * @param bodyStr
	 * @param cls
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ServiceException
	 */
	public static <T> T sendRequest(String url, HttpMethod httpMethod, HeaderParams headerParams, String bodyStr, Class<T> cls)
			throws IllegalArgumentException, ServiceException {
		if(null == headerParams){
			throw new IllegalArgumentException("headerParams can not be empty!");
		}
		
		bodyStr = (StringUtils.isEmpty(bodyStr)) ? " " : bodyStr; //body传" "避免空body导致http异常
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> entity = RestUtils.initRestHeader(restTemplate, headerParams, bodyStr); 
		
		ResponseEntity<T> result = null;
		try {
			result = restTemplate.exchange(url, httpMethod, entity, cls);
		} catch (RestClientException e) {
			logger.error("{}", e);
			throw new ServiceException(ServiceException.DEFAULT_FAIL_CODE, "发送http请求失败." + e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			logger.error("{}", e);
			throw new ServiceException(ServiceException.DEFAULT_FAIL_CODE, "请求返回数据类型与参数返回类型不一致." + e.getMessage());
		}
		
		return result.getBody();
	}
	
	/**
	 * 初始化restTemplate,设置入默认链接超时、读取时间等属性
	 * @param restTemplate
	 * @param hParams
	 */
	public static void initConnectConfig(RestTemplate restTemplate, HeaderParams hParams){
		if (restTemplate.getRequestFactory() instanceof SimpleClientHttpRequestFactory) {
			((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(hParams.getConnectTimeout());
			((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(hParams.getReadTimeout());
		} else if (restTemplate.getRequestFactory() instanceof HttpComponentsClientHttpRequestFactory) {
			((HttpComponentsClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(hParams.getConnectTimeout());
			((HttpComponentsClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(hParams.getReadTimeout());
		}
	}
	
    /**
	 * 初始化http请求头
	 * @param restTemplate
	 * @param hParams
	 * @param bodyStr
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static HttpEntity<String> initRestHeader(RestTemplate restTemplate, HeaderParams hParams, String bodyStr) throws IllegalArgumentException {
		//初始化restTemplate,设置入默认链接超时、读取时间等属性
		initConnectConfig(restTemplate, hParams); //应该在设置Interceptors前面,否则设置超时时间时restTemplate.getRequestFactory()取的为InterceptingClientHttpRequestFactory对象设置不了超时等属性
		
		/** 设置Interceptors */
		if(null != hParams.getInterceptors() && !hParams.getInterceptors().isEmpty()){
			for(ClientHttpRequestInterceptor interceptor: hParams.getInterceptors()){
				restTemplate.getInterceptors().add(interceptor);
			}
		}
		
		if(StringUtils.isEmpty(bodyStr)){
			throw new IllegalArgumentException("bodyStr param is not empty!");
		}
		
		HttpHeaders headers = new HttpHeaders();
		
		/** 设置content-type 与accept */
		if(!StringUtils.isEmpty(hParams.getContentType())){
			MediaType mediaType = MediaType.parseMediaType(hParams.getContentType());
			headers.setContentType(mediaType);
		}
		
		if(!StringUtils.isEmpty(hParams.getAccept())){
			MediaType mediaType = MediaType.parseMediaType(hParams.getAccept());
			List<MediaType> acceptableMediaTypes = new Vector<MediaType>(1);
			acceptableMediaTypes.add(mediaType);
			headers.setAccept(acceptableMediaTypes);
		}
		
		/** 设置拓展参数 */
		if(null != hParams.getParams() && !hParams.getParams().isEmpty()){
			for(Map.Entry<String, String> entry : hParams.getParams().entrySet()){
				headers.add(entry.getKey(), entry.getValue().toString());
			}
		}
		
		return new HttpEntity<String>(bodyStr, headers);
	}
	
	/**
     * Spring RestTemplate 下载组件
     */
    public static class DownloadInterceptor implements ClientHttpRequestInterceptor{
        /** 媒体类型,给个默认值 */
        private MediaType headerValue = MediaType.ALL;

        public MediaType getHeaderValue() {
            return headerValue;
        }

        public void setHeaderValue(MediaType headerValue) {
            this.headerValue = headerValue;
        }

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		    HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
		    List<MediaType> list = new ArrayList<>();
		    list.add(headerValue);
		    requestWrapper.getHeaders().setAccept(list);
		    return execution.execute(requestWrapper, body);
		}
    }
    
    /**
	 * 下载通用方法
	 * @param url 下载url地址
	 * @param headerParams
	 * @return 下载流
	 */
    private static byte[] downFileRequest(String url, HeaderParams headerParams) 
    		throws IllegalArgumentException, ServiceException {
    	if(null == headerParams){
    		headerParams = HeaderParams.Builder.contentType(null);
		}

        ClientHttpRequestInterceptor interceptor = new DownloadInterceptor();
        headerParams.interceptor(interceptor);

        return sendRequest(url, HttpMethod.GET, headerParams, byte[].class);
    }
	
    public static void main(String[] args) {
		String url = "https://www.baidu.com/img/bd_logo1.png";
		
		HeaderParams params = HeaderParams.Builder
				.contentType(null)
				.accept(null)
				.connectTimeout(10000)
				.readTimeout(10000);
		
		String folder = "D://Program Files (x86)/temp/hisDown/";
		String fileName = "2.png";
		
		try {
			downFile(url, folder, fileName, params);
		} catch (IllegalArgumentException e) {
			logger.error("下载失败1,"+e.getMessage());
		} catch (ServiceException e) {
			logger.error("下载失败2,"+e.getMessage());
		}
		
		System.out.println("down success!");
	}
    
}
