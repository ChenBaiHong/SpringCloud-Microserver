package com.baihoomuch.cloud.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Description: sell
 * auther Administrator on 2018/6/26
 * <p>
 * 如果表名（product_category）采用【@Entity】这个注解类词映射成对应实体类（ProductCategory）却无法组成驼峰式
 * <p>
 * 解决方式如下
 * 引入【@Entity】这个注解类词必须表名（product_category）和对应实体类（ProductCategory）能组成驼峰式 ,
 * 否则的采用
 *
 * @Table(name = "s_product_category")
 * 引入这样的格式！
 */
@Entity
@DynamicUpdate //动态更新，比如对应数据库最后更新时间
//@Data //lombok.Data; 包含了一系列 getter，setter，toString 方法
//@Getter  //lombok.Getter ，只包含getter方法
//@Setter  //lombok.Setter ，只包含setter方法
public class ProductCategory {
    /**
     * @param categoryId 类目表主键id，自增类型
     * @param categoryName 类目名称
     * @param categoryType 类目编号
     */
    @Id
    @GeneratedValue
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

    
}
