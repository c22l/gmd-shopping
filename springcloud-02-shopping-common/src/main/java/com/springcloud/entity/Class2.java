package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CLASS2表对应的实体类：用于保存表中一行二级类别信息
 * 
 * @author 曹江柳
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class2 implements java.io.Serializable {

	private static final long serialVersionUID = 13851492467804786L;

	/**
	 * 	类别二编号
	 */
    private Integer class2Id;

    /**
	 * 	类别二名称
	 */
    private String class2Name;

    /**
	 * 	类别一编号
	 */
    private Integer class1Id;

    /**
	 * 	类别二备注
	 */
    private String remark;
}