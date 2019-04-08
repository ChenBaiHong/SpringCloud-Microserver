package com.baihoo.cloud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.baihoo.cloud.criteria.MeCriteria;
import com.baihoo.cloud.domain.MyFile;

/**
 * File 服务接口.
 * @author Administrator
 *
 */
public interface MyFileService {
	/**
	 * 保存文件
	 * @param MyFile
	 * @return
	 */
	MyFile saveFile(MyFile file) throws Exception ;
	
	/**
	 * 删除文件
	 * @param MyFile
	 * @return
	 */
	void removeFile(String id)throws Exception ;
	
	/**
	 * 根据id获取文件
	 * @param MyFile
	 * @return
	 */
	Optional<MyFile> getFileById(String id)throws Exception ;

	/**
	 * 分页查询，按上传时间降序
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<MyFile> listFilesByPage(int pageIndex, int pageSize) throws Exception ;
	/**
	 * 分页查询，按上传时间降序
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	Page<MyFile> listFilesByPage(Pageable pageable) throws Exception ;
	/**
	 * 分页条件查询，按上传时间降序
	 * @param page
	 * @param meCriteria
	 * @return
	 */
	Page<MyFile> listFilesByPage(Pageable pageable , MeCriteria meCriteria) throws Exception ;
}
