package com.springcloud.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.Service.OrdersService;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrdersMapper;
import com.springcloud.entity.Orders;

/**
 * 订单模块模型层的实现类，用于实现订单模块的方法
 * 
 * @author 曹江柳
 *
 */
@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper ordersMapper;
	
	@Override
	public PageInfo<Orders> selectOrders(Orders orders, Integer pageNumber) {
		//在用户名字两端加上%
		if(orders.getUser() != null) {
			orders.getUser().setUserName("%" + orders.getUser().getUserName() + "%");
		}
		//设置分页的信息
		PageHelper.startPage(pageNumber + 1,PageUtils.PAGE_ROW_COUNT);
		//查询满足条件的订单信息
		List<Orders> list = this.ordersMapper.selectOrders(orders);
		//返回分页信息
		return new PageInfo<>(list);
	}

	@Transactional  //事务(增、删、改需要事务，查询不需要事务)
	@Override
	public Integer updateOrderStatus(Orders orders) {	
		return this.ordersMapper.updateOrderStatus(orders);
	}

	@Override
	public List<Orders> selectGroup(Orders orders) {
		return this.ordersMapper.selectGroup(orders);
	}

}
