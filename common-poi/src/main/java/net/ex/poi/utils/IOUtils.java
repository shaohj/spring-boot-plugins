package net.ex.poi.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils {

	private static Logger logger = LoggerFactory.getLogger(IOUtils.class);
	
	public static void close(Closeable c){
		if(null == c){
			return;
		}
		try {
			c.close();
		} catch (IOException e) {
			logger.error("Unable to close resource: \n{}", e);
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
