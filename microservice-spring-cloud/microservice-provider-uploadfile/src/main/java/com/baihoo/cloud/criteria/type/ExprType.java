package com.baihoo.cloud.criteria.type;

import lombok.Data;

/**
 * 细化类的查询类别
 * @author Administrator
 *
 */
@Data
public class ExprType {
	/**
	 * @param  _property 类字段
	 * @param  _op 查询条件
	 * @param  _value 值
	 * @param  _min 最小值
	 * @param  _max 最大值
	 */
	private String _property;
	private String _op;
	private Object _value;
	private Object _min;
	private Object _max;

}
