package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GOODS表对应的实体类：用于封装GOODS表中一行商品信息
 * 
 * @author 曹江柳
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements java.io.Serializable {

	private static final long serialVersionUID = -4402382128795361272L;

	/**
	 * 商品编号
	 */
	private Integer goodsId;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品价格
	 */
	private Double goodsPrices;

	/**
	 * 商品折扣价
	 */
	private Double goodsDiscount;

	/**
	 * 商品状态：0为上架，1为下架
	 */
	private Integer goodsStatus;

	/**
	 * 商品数量
	 */
	private Integer goodsCount;

	/**
	 * 商品是否新品：0是新品，1是非新品
	 */
	private Integer goodsIsNew;

	/**
	 * 商品是否热卖：0是热卖，1是非热卖
	 */
	private Integer goodsIsHot;

	/**
	 * 商品级别：0为1星，1为2星，2为3星，3是4星，4是5星
	 */
	private Integer goodsLevel;

	/**
	 * 商品简介
	 */
	private String goodsBrief;

	/**
	 * 商品详情
	 */
	private String goodsDetails;

	/**
	 * 商品图片
	 */
	private String goodsPhoto;

	/**
	 * 类别二编号
	 */
	private Integer class2Id;

	/**
	 * 查询条件：商品价格下限
	 */
	private Double goodsPricesMin;

	/**
	 * 查询条件：商品价格上限
	 */
	private Double goodsPricesMax;

	/**
	 * 查询条件：类别一的编号
	 */
	private Integer class1Id;

	/**
	 * 商品销量：用于保存统计分组的结果
	 */
	private Integer goodsSum;

}