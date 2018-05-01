package net.sf.excelutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class FileUtils {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * 复制文件
	 * @param fromFileUrl 文件路径(含文件名及其后缀)
	 * @param toFileUrl 文件路径(含文件名及其后缀)
	 * @throws IOException
	 */
	public static void copyFile(String fromFileUrl, String toFileUrl) throws IOException{
		File fromFile = new File(fromFileUrl);
		File toFile = new File(toFileUrl);
		
		copyFile(fromFile, toFile);
	}
	
	/**
	 * 复制文件
	 * @param fromFile
	 * @param toFile
	 * @throws IOException
	 */
	public static void copyFile(File fromFile, File toFile) throws IOException{
		if (!toFile.exists()) {
			toFile.getParentFile().mkdirs();
		}
		
		InputStream in = null;
		OutputStream out = null;
		
		try {
			in = new FileInputStream(fromFile);
			out = new FileOutputStream(toFile);
			
			IOUtils.readAndWriteFile(in, out);
		} finally {
			IOUtils.close(in);
			IOUtils.close(out);
		}
	}
	
	/**
	 * 删除空目录
	 * @param dir 将要删除的目录路径
	 */
	private static boolean doDeleteEmptyDir(String dir) {
		return (new File(dir)).delete();
	}

	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean  
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

	public static void main(String[] args) {
		//删除空目录或文件
//		String emptyPath = "C:/Users/dell/Desktop/temp/reports/empty";
//		System.out.println("success="+doDeleteEmptyDir(emptyPath));

		//递归删除文件
//		String path = "C:/Users/dell/Desktop/temp/reports/modules";
//		
//		long t1 = System.currentTimeMillis();
//		boolean success = deleteDir(new File(path));
//		
//		System.out.println("success="+success);
//		System.out.println(System.currentTimeMillis() - t1);
		
		//复制单个文件
//		String fromFileUrl = "C:/Users/dell/Desktop/temp/down/20170703/1520335550000/07031520335540000.xlsx";
//		String toFileUrl = "C:/Users/dell/Desktop/temp/reports/07031520335540000.xlsx";
//		
//		try {
//			copyFile(fromFileUrl, toFileUrl);
//		} catch (IOException e) {
//			logger.error("IOException", e);
//		}
	}

}
