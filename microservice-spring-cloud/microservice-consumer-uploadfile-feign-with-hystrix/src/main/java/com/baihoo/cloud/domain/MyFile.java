package com.baihoo.cloud.domain;

import java.util.Date;

import lombok.Data;


/**
 * File 文档类.
 * @author Administrator
 * <br>
 *注意：这里是非关系数据库 NoSQL 建立映射实体的对象
 */
@Data
public class MyFile {
	/**
	 * @param id 						主键
	 * @param name				文件名称
	 * @param contentType	文件类型
	 * @param size 					文件大小
	 * @param uploadDate 		上载日期
	 * @param md5	md5 		加密
	 * @param content 			文件内容
	 * @param path 				文件路径
	 */
	private String id;
    private String name; 
    private String contentType; 
    private long size;
    private Date uploadDate;
    private String md5;
    private String content; 
    private String path; 
    
}
