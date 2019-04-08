package com.baihoo.cloud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.baihoo.cloud.domain.MyFile;
 

/**
 * File 存储库.<br>
 * 		采用MongoDB操作
 * 	
 * @author Administrator
 *
 */
public interface MyFileRepository extends MongoRepository<MyFile, String> {
}
