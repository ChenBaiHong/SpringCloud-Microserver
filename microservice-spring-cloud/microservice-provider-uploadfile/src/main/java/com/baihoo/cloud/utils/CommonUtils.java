package com.baihoo.cloud.utils;

import java.io.File;

/**
 * 
 * @author Administrator
 *
 */
public class CommonUtils {
	/**
	 * windows系统，一个文件夹里如果有一千个文件打开就会非常缓慢，影响访问性能，因此要解决这个问题
	 * 
	 * @param filepath
	 * @param filename
	 * @return
	 */
	public static String generateScatterFilepath(String filepath, String filename) {
		int directory1 = filename.hashCode() & 15;// oxf等同于15，二进制与位符
		int directory2 = (filename.hashCode() >> 4) & 0xf;// 二进制 >>右移的数据一个hashCode相反的数据
		filepath = filepath + "/" + directory1 + "/" + directory2;
		File file = new File(filepath);
		if (!file.exists()) {// 判断文件目录是否存在，如果不存在就生成该目录
			file.mkdirs();// Creates the directory named by this abstract pathname
		}
		return filepath + "/" + filename;
	}
	/**
	 * 刪除文件
	 * @param fileName
	 */
	public static void deleteFileInfo(String fileName) {
		if (fileName != null) {
			File file = new File(fileName);
			if (file.exists()) {
				if (file.isFile()) {
					file.delete();
				}
			}
		}
	}
	/**
	 * 创建文件夹
	 * @param fileDire
	 */
	public static void createFolder(String fileDire) {
		if (fileDire != null) {
			File file = new File(fileDire);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}
}
