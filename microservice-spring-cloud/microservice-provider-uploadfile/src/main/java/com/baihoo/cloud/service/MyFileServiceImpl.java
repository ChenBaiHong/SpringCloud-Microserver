package com.baihoo.cloud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.baihoo.cloud.criteria.MeCriteria;
import com.baihoo.cloud.criteria.type.ExprType;
import com.baihoo.cloud.domain.MyFile;
import com.baihoo.cloud.repository.MyFileRepository;
import com.baihoo.cloud.utils.DateUtils;

/**
 * File 服务.
 * @author Administrator
 *
 */
@Service
public class MyFileServiceImpl implements MyFileService {
	/**
	 * @param fileRepository 
	 * @param mongoTemplate 
	 */
	@Autowired
	public MyFileRepository myFileRepository;
	@Autowired
    protected MongoTemplate mongoTemplate;
	
	/**
	 *保存文件
	 */
	@Override
	public MyFile saveFile(MyFile file) throws Exception {
		return myFileRepository.save(file);
	}
	/**
	 * 删除文件
	 */
	@Override
	public void removeFile(String id) throws Exception {
		myFileRepository.deleteById(id);
	}
	/**
	 * 根据id获取文件
	 */
	@Override
	public Optional<MyFile> getFileById(String id) throws Exception {
		return myFileRepository.findById(id);
	}
	/**
	 * 分页查询，按上传时间降序
	 */
	@Override
	public List<MyFile> listFilesByPage(int pageIndex, int pageSize) throws Exception {
		Page<MyFile> page = null;
		List<MyFile> list = null;
		
		Sort sort = new Sort(Direction.DESC,"uploadDate"); 
		Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
		
		page = myFileRepository.findAll(pageable);
		list = page.getContent();
		return list;
	}

	@Override
	public Page<MyFile> listFilesByPage(Pageable pageable) throws Exception {
		Page<MyFile> page = myFileRepository.findAll(pageable);
		return page;
	}
	/**
	 *分页条件查询，按上传时间降序
	 * @throws Exception 
	 */
	@Override
	public Page<MyFile> listFilesByPage(Pageable pageable, MeCriteria meCriteria) throws Exception {
		
		List<ExprType> expr= meCriteria.get_expr();
		
		//定义查询Criteria链
		 List<Criteria> criteriaChain = new ArrayList<Criteria>();;
		for (ExprType exprType : expr) {
			String property = exprType.get_property();
			if(exprType.get_value()!=null) {
				Object value = exprType.get_value();
				//mongoDB模糊查询
				Pattern pattern = Pattern.compile("^.*" + value + ".*$");
				if("name".equals(property)) {
					criteriaChain.add(Criteria.where("name").regex(pattern));
				}else if("contentType".equals(property)) {
					criteriaChain.add(Criteria.where("contentType").regex(pattern));
				}
			}else if( exprType.get_min() !=null &&  exprType.get_max()!=null){
				Object min = exprType.get_min();
				Object max = exprType.get_max();
				if(exprType.get_property().equals("uploadDate")) {
					if(min !=null && max !=null && !"".equals(min) && !"".equals(max)) { 
						//大于最小时间,小于最大时间
						criteriaChain.add(
								Criteria.where("uploadDate").gt(DateUtils.parseDate((String) min, "yyyy-MM-dd HH:mm"))
										.lt(DateUtils.parseDate((String) max, "yyyy-MM-dd HH:mm")));
					}
				}else if(exprType.get_property().equals("size")){
					if(min !=null && max !=null && !"".equals(min) && !"".equals(max)) { 
						//大于最小size,小于最大size
						criteriaChain.add(Criteria.where("size").gt(Long.parseLong((String)min)).lt(Long.parseLong((String)max)));
					}
				}
				
			}
		}
		Criteria criteria =new Criteria();
		criteria.andOperator(criteriaChain.toArray(new Criteria[criteriaChain.size()]));
		//查询，加入criteria组成条件
		Query query=new Query(criteria);
		//分页查询
		List<MyFile>  list = mongoTemplate.find(query.with(pageable), MyFile.class);
		//返回分页查询的对象
		Page<MyFile> page = new PageImpl<MyFile>(list , pageable, mongoTemplate.count(query, MyFile.class));
		return page;
	}
}
