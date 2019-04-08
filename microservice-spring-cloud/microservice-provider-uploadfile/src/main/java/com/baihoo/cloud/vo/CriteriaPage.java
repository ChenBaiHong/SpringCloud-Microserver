package com.baihoo.cloud.vo;

import com.baihoo.cloud.criteria.MeCriteria;

import lombok.Data;

/**
 * 
 * @author Administrator
 *
 */
@Data
public class CriteriaPage {

	private boolean async;

	private Integer pageIndex;

	private Integer pageSize;

	private MeCriteria meCriteria;

	public CriteriaPage() {
	}

	public CriteriaPage(boolean async, Integer pageIndex, Integer pageSize) {
		this.async = async;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public CriteriaPage(boolean async, Integer pageIndex, Integer pageSize, MeCriteria meCriteria) {
		this(async, pageIndex, pageSize);
		this.meCriteria = meCriteria;
	}

}
