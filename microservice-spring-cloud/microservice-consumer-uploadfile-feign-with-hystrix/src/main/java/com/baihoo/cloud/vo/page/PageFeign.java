package com.baihoo.cloud.vo.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Data;

/**
 * spring Cloud 调用  feign page Pageable 分页查询
 * 
 * @author Administrator
 *
 * @param <T>
 */
@SuppressWarnings("serial")
@Data
public class PageFeign<T>  implements Iterable<T>, Serializable {
	
	private List<T> content = new ArrayList<T>();
	private boolean first;
	private boolean last;
	private int totalPages;
	private long totalElements;
	private int numberOfElements;
	private int size;
	private int number;
	/*private Pageable pageable;*/
	@Override
	public Iterator<T> iterator() {
		return content.iterator();
	}

}
