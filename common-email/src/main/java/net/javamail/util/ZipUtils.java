package net.javamail.util;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.alibaba.fastjson.util.IOUtils;

import net.javamail.model.param.AttachFileParam;
import net.javamail.model.param.ZipParam;

/**
 * 压缩工具类
 * 编  号：<br/>
 * 名  称：ZipUtils<br/>
 * 描  述：<br/>
 * 完成日期：2017年9月27日 下午4:16:26<br/>
 * 编码作者：ShaoHj<br/>
 */
public class ZipUtils {

	/**
	 * 
	 * @Title: zip 
	 * @param zipParam
	 * @param filesParam
	 * @throws IOException
	 */
	public static void zip(ZipParam zipParam, List<AttachFileParam> filesParam) throws IOException {
		if(null == zipParam || null == filesParam){
			return ;
		}
		
		FileUtils.createParentDirs(zipParam.getPath());
		
		ZipOutputStream zout = new ZipOutputStream(
				new FileOutputStream(zipParam.getPath()));
		
		BufferedOutputStream bos = new BufferedOutputStream(zout);
		
		for(AttachFileParam fileParam : filesParam){
			putFile(zout, bos, fileParam);;
		}
		
		IOUtils.close(bos);
		IOUtils.close(zout);
	}
	
	/**
	 * 
	 * @Title: putFile 
	 * @param zout
	 * @param bos
	 * @param fileParam
	 * @throws IOException
	 */
	private static void putFile(ZipOutputStream zout,
			BufferedOutputStream bos, AttachFileParam fileParam) throws IOException{
		zout.putNextEntry(new ZipEntry(fileParam.getName()));
		
		File file = new File(fileParam.getPath());
		
		if(file.isDirectory()){
			return ; //目前只添加文件,不添加文件夹
		}
		
		FileInputStream fin = new FileInputStream(file);
		BufferedInputStream fbis = new BufferedInputStream(fin);
		
		byte[] b = new byte[1024];
		
		int len = fbis.read(b);
		while(len > 0){
			bos.write(b, 0 ,len);
			len = fbis.read(b);
		}
		
		IOUtils.close(fbis);
		IOUtils.close(fin);
		
		bos.flush();
	}
}
