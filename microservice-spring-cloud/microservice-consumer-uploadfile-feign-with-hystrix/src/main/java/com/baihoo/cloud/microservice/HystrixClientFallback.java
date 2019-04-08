package com.baihoo.cloud.microservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.baihoo.cloud.criteria.MeCriteria;
import com.baihoo.cloud.domain.MyFile;
import com.baihoo.cloud.vo.CriteriaPage;
import com.baihoo.cloud.vo.ResultVO;
import com.baihoo.cloud.vo.ResultVOUtil;
import com.baihoo.cloud.vo.page.PageFeign;

/**
 * FeignClient 远程微服务调用，若调用超时或调用出现异常，启动hystrix断路器模式
 * 
 * @author Administrator
 *
 */
@Component
@SuppressWarnings("all")
public class HystrixClientFallback implements FileFeignClient {

	@Override
	public Page<MyFile> msquery(boolean async, Integer pageIndex, Integer pageSize, MeCriteria meCriteria) {
		return null;
	}
	@Override
	public ResultVO<String> msquery2(boolean async, Integer pageIndex, Integer pageSize) {
		return ResultVOUtil.error(500, "微服务调用未接收到值");
	}
	@Override
	public ResultVO<PageFeign<MyFile>> msquery3(CriteriaPage criteriaPage) {
		return  ResultVOUtil.error(500, "微服务调用未接收到值");
	}
	@Override
	public ResultVO<MyFile> getMyFile(String id) {
		return ResultVOUtil.error(500, "微服务调用未接收到值");
	}
	@Override
	public ResultVO<String> deleteMyFile(String id) {
		// TODO Auto-generated method stub
		return ResultVOUtil.error(500, "微服务调用未接收到值");
	}
	@Override
	public ResultVO<String> handleMyFileUpload(MultipartFile file) {
		// TODO Auto-generated method stub
		return ResultVOUtil.error(500, "微服务调用未接收到值");
	}

}
