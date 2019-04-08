package com.baihoo.cloud.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baihoo.cloud.criteria.CriteriaType;
import com.baihoo.cloud.criteria.MeCriteria;
import com.baihoo.cloud.domain.MyFile;
import com.baihoo.cloud.microservice.FileFeignClient;
import com.baihoo.cloud.vo.CriteriaPage;
import com.baihoo.cloud.vo.ResultVO;
import com.baihoo.cloud.vo.page.PageFeign;
import com.google.gson.Gson;


/**
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("all")
// @CrossOrigin(origins = "*", maxAge = 3600) // 允许所有域名访问，响应最大时间
@RestController
public class FileController {
	
	/**
	 * @param loader			 Spring提供的资源加载器
	 */
	@Autowired  
	ResourceLoader loader;  
	@Resource
	private FileFeignClient  fileFeignClient;


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
		
		ResultVO<PageFeign<MyFile>> result = 
				fileFeignClient.msquery3(new CriteriaPage(async, pageIndex,  pageSize));
		model.addAttribute("files", result.getData().getContent());
		model.addAttribute("page", result.getData());
		
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
		
		ResultVO<PageFeign<MyFile>> result = 
				fileFeignClient.msquery3(new CriteriaPage(async, pageIndex,  pageSize , criteriaType.getMeCriteria()));
		model.addAttribute("files", result.getData().getContent());
		model.addAttribute("page", result.getData());
		
		return new ModelAndView("index" , "model" , model);
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
		ResultVO<PageFeign<MyFile>> result = 
				fileFeignClient.msquery3(new CriteriaPage(false, pageIndex,  pageSize));
		return  result.getData().getContent();
	}

	/**
	 * 下载文件
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/files/{id}")
	public ResponseEntity<Object> serveFile(@PathVariable String id) throws Exception {

		ResultVO<MyFile> result = fileFeignClient.getMyFile(id);
		MyFile file = result.getData();
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; fileName=" + new String(file.getName().getBytes("utf-8"), "ISO-8859-1"))
				.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
				.header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "").header("Connection", "close")
				//通过文件地址读取文件字节
				.body(IOUtils.toByteArray(
						loader.getResource("file:"+file.getContent())
						.getInputStream()
						));
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
		ResultVO<MyFile> result = fileFeignClient.getMyFile(id);
		MyFile file = result.getData();
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + file.getName() + "\"")
				.header(HttpHeaders.CONTENT_TYPE, file.getContentType())
				.header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "").header("Connection", "close")
				//通过文件地址读取文件字节
				.body(IOUtils.toByteArray(
						loader.getResource("file:"+file.getContent())
						.getInputStream()
						));
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
		ResultVO<String> result = fileFeignClient.handleMyFileUpload(file);
		if(result.getCode() == 200) {
			return ResponseEntity.status(HttpStatus.OK).body("upload Success!");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.getMsg());
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFile(@PathVariable String id) throws Exception{
		ResultVO<String> result = fileFeignClient.deleteMyFile(id);
		if(result.getCode() == 200) {
			return ResponseEntity.status(HttpStatus.OK).body("DELETE Success!");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.getMsg());
		}
	}
}


