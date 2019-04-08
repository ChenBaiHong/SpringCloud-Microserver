package com.baihoo.cloud.criteria;

import java.util.List;

import com.baihoo.cloud.criteria.type.ExprType;
import com.baihoo.cloud.criteria.type.OrderByType;
import com.baihoo.cloud.criteria.type.OrderType;

import lombok.Data;

/**
 * 组织的类型，定义成自己的标准查询
 * @author Administrator
 *
 */
@Data
public class MeCriteria {
	/**
	 * @param _distinct 是否去重
	 * @param _entity 实体
	 * @param _expr	操作字段
	 * @param _and    
	 * @param order
	 * @param orderBy 
	 */
	private Boolean _distinct;
	private String _entity;
	private List<ExprType> _expr ;
	private List<OrderType> order ;
	private List<OrderByType> orderBy ;
}
