package com.baihoo.cloud.controller;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baihoo.cloud.criteria.CriteriaType;
import com.baihoo.cloud.criteria.MeCriteria;
import com.baihoo.cloud.domain.MyFile;
import com.baihoo.cloud.service.MyFileService;
import com.baihoo.cloud.utils.CommonUtils;
import com.baihoo.cloud.utils.MD5Util;
import com.baihoo.cloud.vo.CriteriaPage;
import com.baihoo.cloud.vo.ResultVO;
import com.baihoo.cloud.vo.ResultVOUtil;
import com.baihoo.cloud.vo.page.PageFeign;
import com.baihoo.cloud.vo.page.PageUtil;
import com.google.gson.Gson;

/**
 * 文件服务器controller
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("all")
// @CrossOrigin(origins = "*", maxAge = 3600) // 允许所有域名访问，响应最大时间
@RestController
public class MyFileController {
	
	@Autowired
	private MyFileService myFileService;
	/**
	 * @param loader			 Spring提供的资源加载器
	 */
	@Autowired  
	ResourceLoader loader;  
	
	@Value("${myserver.address}")
	private String serverAddress;
	@Value("${upload.file.address}")
	private String uploadFileAddress;
	@Value("${server.port}")
	private String serverPort;

	/**
	 * 访问文件服务器首页
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	
	@GetMapping("/")
	public ModelAndView index(@RequestParam(value = "async", required = false) boolean async,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			Model model )throws Exception {
		// 展示最新10条数据
		Page<MyFile> page = msquery(async , pageIndex , pageSize, null);
		model.addAttribute("files", page.getContent());
		model.addAttribute("page", page);

		return new ModelAndView("index" , "model" , model);
	}

	/**
	 * 分页携带条件查询文件
	 * 
	 * @param async
	 * @param pageIndex
	 * @param pageSize
	 * @param criteria
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/query")
	public ModelAndView index(@RequestParam(value = "async", required = false) boolean async,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			CriteriaType criteriaType, Model model) throws Exception {
		// 展示最新10条数据
		Page<MyFile> page = msquery(async , pageIndex , pageSize, criteriaType.getMeCriteria());
		model.addAttribute("files", page.getContent());
		model.addAttribute("page", page);
		model.addAttribute("meCriteria", criteriaType.getMeCriteria());
		return  new ModelAndView("index" , "model" , model);
	}

	/**
	 * 分页查询文件
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/files/{pageIndex}/{pageSize}")
	public List<MyFile> listFilesByPage(@PathVariable int pageIndex, @PathVariable int pageSize)
			throws Exception {
		return myFileService.listFilesByPage(pageIndex, pageSize);
	}

	/**
	 * 获取文件片信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/files/{id}")
	public ResponseEntity<Object> serveFile(@PathVariable String id) throws Exception {

		Optional<MyFile> file = myFileService.getFileById(id);

		if (file.isPresent()) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; fileName=" + new String(file.get().getName().getBytes("utf-8"), "ISO-8859-1"))
					.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_LENGTH, file.get().getSize() + "").header("Connection", "close")
					//通过文件地址读取文件字节
					.body(IOUtils.toByteArray(
							loader.getResource("file:"+file.get().getContent())
							.getInputStream()
							));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
		}

	}

	/**
	 * 在线显示文件
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/view/{id}")
	public ResponseEntity<Object> serveFileOnline(@PathVariable String id) throws Exception {

		Optional<MyFile> file = myFileService.getFileById(id);
		// 判断是否存在值
		if (file.isPresent()) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + file.get().getName() + "\"")
					.header(HttpHeaders.CONTENT_TYPE, file.get().getContentType())
					.header(HttpHeaders.CONTENT_LENGTH, file.get().getSize() + "").header("Connection", "close")
					//通过文件地址读取文件字节
					.body(IOUtils.toByteArray(
							loader.getResource("file:"+file.get().getContent())
							.getInputStream()
							));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
		}

	}

	/**
	 * 上传文件的接口
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file)
			throws Exception {
		MyFile myfile = null;
		try {
			// 1、 判断上载文件是否是空文件
			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("上传文件为空");
			}
			 // 2、解决windows系统文件加载性能问题
			String content = CommonUtils.generateScatterFilepath(uploadFileAddress, file.getOriginalFilename());
			// 3、我的文件对象详情填入
			myfile = new MyFile(file.getOriginalFilename(), // 获取文件名, 
					file.getContentType(),  // 获取文件的类型
					file.getSize(),// 获取文件大小
					content);
			myfile.setMd5(MD5Util.getMD5(file.getInputStream()));
			// 4. 文件写入保存的硬盘地址
			file.transferTo(new File(content));
			// 5、MongoDB保存
			myfile = myFileService.saveFile(myfile);
			// 6、展示图片的url地址
			String path = "//" + serverAddress + ":" + serverPort + "/view/" + myfile.getId();
			return ResponseEntity.status(HttpStatus.OK).body(path);
		} catch (IOException | NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFile(@PathVariable String id) {

		try {
			myFileService.removeFile(id);
			return ResponseEntity.status(HttpStatus.OK).body("DELETE Success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 *  TODO Fegin调用不到该服务方
	 *  	错误2点：
	 *  		1、返回接受的类型不能是接口和抽线类，并且类里面属性字段也不能有接口和抽线类，否则Fegin调用不到该服务方。JDK集合类，定义类除外
	 *  		2、Fegin 接口中的方法不支持@RequestBody和@RequestParam 共存，否则Fegin调用不到该服务方
	 *  
	 * @param async
	 * @param pageIndex
	 * @param pageSize
	 * @param meCriteria
	 * @throws Exception
	 */
	@PostMapping("/msquery")
	public Page<MyFile> msquery( boolean async,Integer pageIndex, Integer pageSize ,@RequestBody MeCriteria meCriteria)throws Exception {
		// 展示最新10条数据
		Sort sort = new Sort(Direction.DESC, "uploadDate");
		Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
		Page<MyFile> page = null;
		if(meCriteria !=null && meCriteria.get_entity()!=null)
			page = myFileService.listFilesByPage(pageable, meCriteria);
		else
			page = myFileService.listFilesByPage(pageable);
		return page;
	}
	/**
	 * TODO 以json字符串数据格式传递给Feign调用方
	 * 注意：Fegin 接口中的方法不支持@RequestBody和@RequestParam 共存，否则Fegin调用不到该服务方
	 * @param meCriteria
	 * @param async
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/msquery2")
	public ResultVO<String> msquery2(boolean async,Integer pageIndex, Integer pageSize) throws Exception {
		// 展示最新10条数据
		Sort sort = new Sort(Direction.DESC, "uploadDate");
		Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
		Page<MyFile> page = null;
		page = myFileService.listFilesByPage(pageable);
		Gson gson = new Gson();
		String json = gson.toJson(PageUtil.SpringPageToPageFeign(page) , PageFeign.class);
		return ResultVOUtil.success(json);
	}
	/**
	 * TODO 
	 * 注意：
	 * 		1、Fegin 接口中的方法不支持@RequestBody和@RequestParam 共存，否则Fegin调用不到该服务方
	 * 		2、返回接受的类型不能是接口和抽线类，并且类里面属性字段也不能有接口和抽线类，否则Fegin调用不到该服务方。JDK集合类，定义类除外
	 * @param async
	 * @param pageIndex
	 * @param pageSize
	 * @param meCriteria
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/msquery3")
	public ResultVO<PageFeign<MyFile>> msquery3(@RequestBody CriteriaPage criteriaPage) throws Exception {
		// 展示最新10条数据
		if(criteriaPage !=null) {
			Sort sort = new Sort(Direction.DESC, "uploadDate");
			Pageable pageable = PageRequest.of(criteriaPage.getPageIndex(), criteriaPage.getPageSize(), sort);
			Page<MyFile> page = null;
			
			if(criteriaPage.getMeCriteria() !=null && criteriaPage.getMeCriteria().get_entity()!=null)
				page = myFileService.listFilesByPage(pageable, criteriaPage.getMeCriteria());
			else
				page = myFileService.listFilesByPage(pageable);
			
			return ResultVOUtil.success(PageUtil.SpringPageToPageFeign(page));
		}else {
	
			return ResultVOUtil.error(500, "查询错误！");
		}
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/myfiles/{id}")
	public ResultVO<MyFile> getMyFile(@PathVariable String id) throws Exception {
		Optional<MyFile> optional = myFileService.getFileById(id);
		if (optional.isPresent()) {
			return ResultVOUtil.success(optional.get());
		} else {
			return ResultVOUtil.error(500 , "文件不存在");
		}
	}
	/**
	 * 微服务调用删除文件
	 * @param id
	 * @return
	 */
	@DeleteMapping("/msc/del/{id}")
	public ResultVO<String> deleteMyFile(@PathVariable String id) {
		try {
			myFileService.removeFile(id);
			return ResultVOUtil.success();
		} catch (Exception e) {
			return ResultVOUtil.error(500 , e.getMessage()); 
		}
	}
	/**
	 * 微服务调用上传文件的接口
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/mscupload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResultVO<String> handleMyFileUpload(@RequestPart("file") MultipartFile file)
			throws Exception {
		ResponseEntity<String> response =  handleFileUpload(file);
		if(response.getStatusCodeValue() == 200) {
			return ResultVOUtil.success();
		}else {
			return ResultVOUtil.error(response.getStatusCodeValue(), response.getBody());
		}
	}
}
