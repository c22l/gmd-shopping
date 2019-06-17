package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CLASS1表对应的实体类:：用于保存表中一行一级类别信息
 * 
 * @author 曹江柳
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class1 implements java.io.Serializable {

	private static final long serialVersionUID = -5300706713029666878L;

	/**
	 * 类别一编号
	 */
	private Integer class1Id;

	/**
	 * 类别一名称
	 */
	private String class1Name;

	/**
	 * 类别一序号
	 */
	private Integer class1Num;

	/**
	 * 类别一备注
	 */
	private String remark;

}