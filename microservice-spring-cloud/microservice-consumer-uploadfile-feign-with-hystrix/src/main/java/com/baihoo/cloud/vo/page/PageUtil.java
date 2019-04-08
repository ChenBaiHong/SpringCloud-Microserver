package com.baihoo.cloud.vo.page;

import org.springframework.data.domain.Page;

/**
 *	Page -> PageFeign
 * @author Administrator
 *
 */
public class PageUtil {
	/**
	 *  Spring的Page 转化成 本地PageFeign
	 * @param page
	 * @return
	 */
	public static <T> PageFeign<T> SpringPageToPageFeign(Page<T> page) {
		PageFeign<T> pageFeign = new PageFeign<T>();
		pageFeign.setContent(page.getContent());
		pageFeign.setFirst(page.isFirst());
		pageFeign.setLast(page.isLast());
		pageFeign.setNumber(page.getNumber());
		pageFeign.setNumberOfElements(page.getNumberOfElements());
		/*pageFeign.setPageable(page.getPageable());*/
		pageFeign.setSize(page.getSize());
		pageFeign.setTotalElements(page.getTotalElements());
		pageFeign.setTotalPages(page.getTotalPages());
		
		return pageFeign;
	}
}
	