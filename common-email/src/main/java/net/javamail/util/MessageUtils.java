package net.javamail.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeUtility;

import com.alibaba.fastjson.util.IOUtils;

/**
 * 编  号：
 * 名  称：MessageUtils
 * 描  述：解析邮件工具类
 * 完成日期：2018/8/4 15:13
 * @author：felix.shao
 */
public class MessageUtils {

	/**
	 * 接卸包裹（含所有邮件内容(包裹+正文+附件)）
	 * @Title: reMultipart 
	 * @param multipart
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void reMultipart(Multipart multipart, String storePath, List<String> pathList) throws MessagingException, IOException {
	    // 依次处理各个部分
	    for (int j = 0, n = multipart.getCount(); j < n; j++) {
			//解包, 取出 MultiPart的各个部分, 每部分可能是邮件内容,
	        Part part = multipart.getBodyPart(j);
	        // 也可能是另一个小包裹(MultipPart)
	        // 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative
	        if (part.getContent() instanceof Multipart) {
				// 转成小包裹
	            Multipart p = (Multipart) part.getContent();
	            //递归迭代
	            reMultipart(p, storePath, pathList);
	        } else {
	        	rePart(part, storePath, pathList);
	        }
	     }
	}
	
	/**
	 * 解析内容
	 * @Title: rePart 
	 * @param part
	 * @param storePath
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void rePart(Part part, String storePath, List<String> pathList) throws MessagingException, IOException {
		if (part.getDisposition() != null) {
			//MimeUtility.decodeText解决附件名乱码问题
			String strFileNmae = MimeUtility.decodeText(part.getFileName());
			// 打开附件的输入流
		    InputStream in = part.getInputStream();
		    
		    String path = storePath + "/" + strFileNmae;
		    pathList.add(path);
		    
		    FileUtils.createDirs(storePath);
		    
		    // 读取附件字节并存储到文件中
		    java.io.FileOutputStream out = new FileOutputStream(path);
		    readAndWriteFile(in, out);
		    IOUtils.close(out);
		    IOUtils.close(in);
		} 
	}
	
	/**
	 * IO流操作,边读边写,经测试(文件大小为8KB时,此性能比一次性读取速度还快)
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void readAndWriteFile(InputStream in, OutputStream out) throws IOException {
		if(null == in || null == out){
			throw new IOException("InputStream or OutputStream param is null");
		}
		
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b)) != -1) {
			out.write(b, 0, len);
		}
	}
	
}
