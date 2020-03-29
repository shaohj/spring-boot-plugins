package com.sprboot.plugin.resttempex.bean.param;

import java.util.*;

import org.springframework.http.client.ClientHttpRequestInterceptor;

/**
 * 编  号：
 * 名  称：HeaderParams
 * 描  述：http header 请求头对象
 * 完成日期：2020/3/29 20:45
 * @author：felix.shao
 */
public class HeaderParams {

	/** Content-Type */
	private String contentType = "";
	
	/** Accept */
	private String accept = "";
	
	/** connectTimeout */
	private int connectTimeout = 30 * 1000; 
	
	/** readTimeout */
	private int readTimeout = 30 * 1000; 

	/** 可放置BasicAuthorizationInterceptor等信息 */
	private List<ClientHttpRequestInterceptor> interceptors; 
	
	/** header 拓展参数 */
	private Map<String, String> params;
	
	private HeaderParams(){}
	
	public HeaderParams contentType(String contentType){
		this.contentType = contentType;
		return this;
	}
	
	public HeaderParams accept(String accept){
		this.accept = accept;
		return this;
	}
	
	public HeaderParams connectTimeout(int connectTimeout){
		this.connectTimeout = connectTimeout;
		return this;
	}
	
	public HeaderParams readTimeout(int readTimeout){
		this.readTimeout = readTimeout;
		return this;
	}
	
	public HeaderParams interceptor(ClientHttpRequestInterceptor interceptor){
		if(null == interceptors){
			interceptors = new Vector<ClientHttpRequestInterceptor>();
		}
		this.interceptors.add(interceptor);
		return this;
	}
	
	public HeaderParams param(String key, String value){
		if(null == params){
			params = new HashMap<>();
		}
		this.params.put(key, value);
		return this;
	}
	
	/**
	 * 
	 * 编  号：<br/>
	 * 名  称：Builder<br/>
	 * 描  述：<br/>
	 * 完成日期：2017年7月26日 下午4:58:30<br/>
	 * 编码作者：ShaoHj<br/>
	 */
	public static class Builder{
		
		public static HeaderParams contentType(String contentType){
			HeaderParams params = new HeaderParams();
			return params.contentType(contentType);
		}
		
		public static HeaderParams accept(String accept){
			HeaderParams params = new HeaderParams();
			return params.accept(accept);
		}
		
		public static HeaderParams connectTimeout(int connectTimeout){
			HeaderParams params = new HeaderParams();
			return params.connectTimeout(connectTimeout);
		}
		
		public static HeaderParams readTimeout(int readTimeout){
			HeaderParams params = new HeaderParams();
			return params.readTimeout(readTimeout);
		}
		
		public static HeaderParams interceptor(ClientHttpRequestInterceptor interceptor){
			HeaderParams params = new HeaderParams();
			return params.interceptor(interceptor);
		}
		
		public static HeaderParams param(String key, String value){
			HeaderParams params = new HeaderParams();
			return params.param(key, value);
		}
		
	}

	public String getContentType() {
		return contentType;
	}

	public String getAccept() {
		return accept;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public List<ClientHttpRequestInterceptor> getInterceptors() {
		return interceptors;
	}

	public Map<String, String> getParams() {
		return params;
	}


	public static HeaderParams getDefaultHeaderParams(){
		return Builder
				.contentType("application/json; charset=utf-8")
				.accept("application/json; charset=utf-8");
	}

}
