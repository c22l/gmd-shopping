package com.springcloud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.Service.OrdersService;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Orders;
import com.springcloud.vo.ResultValure;

/**
 * 订单模块的控制层
 * 
 * @author 曹江柳
 *
 */
@RestController
@RequestMapping("orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	/**
	 * 查询满足条件的订单信息
	 * 
	 * @param orders     查询条件
	 * @param pageNumber 页数
	 * @return
	 */
	@RequestMapping(value = "/selectOrders")
	public ResultValure selectOrders(Orders orders, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValure rv = new ResultValure();

		try {
			// 查询满足条件的订单信息
			PageInfo<Orders> pageInfo = this.ordersService.selectOrders(orders, pageNumber);
			// 从分页信息中获得商品的数据
			List<Orders> list = pageInfo.getList();
			// 如果查询到了满足条件的商品信息
			if (list != null && list.size() > 0) {
				rv.setCode(0);

				// 创建Map集合保存到查询结果
				Map<String, Object> map = new HashMap<>();
				// 将查询结果保存到Map集合中
				map.put("ordersList", list);

				PageUtils pageUtils = new PageUtils(pageInfo.getPages() * PageUtils.PAGE_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);
				// 将分页信息以指定的名字存入Map集合中
				map.put("pageUtils", pageUtils);

				// 将Map添加到ResultValue中
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("没有找到满足条件的订单信息！！！");
		return rv;
	}

	/**
	 * 修改指定编号订单的订单状态
	 * 
	 * @param orders 修改的订单信息
	 * @return
	 */
	@RequestMapping(value = "/updateOrderStatus")
	public ResultValure updateOrderStatus(Orders orders) {
		ResultValure rv = new ResultValure();

		try {
			Integer status = this.ordersService.updateOrderStatus(orders);
			if (status > 0) {
				rv.setCode(0);
				rv.setMessage("订单的状态修改成功!!!");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		rv.setCode(1);
		rv.setMessage("订单的状态修改失败!!!");
		return rv;
	}

	/**
	 * 查询指定日期范围内的销量额
	 * 
	 * @param orders 查询的条件
	 * @return
	 */
	@RequestMapping(value = "/selectGroup")
	public ResultValure selectGroup(Orders orders) {
		ResultValure rv = new ResultValure();

		try {
			List<Orders> list = this.ordersService.selectGroup(orders);
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				// 创建两个集合，用于保存柱状图x抽与y抽的数据
				List<String> x = new ArrayList<>();
				List<Double> y = new ArrayList<>();
				//将查询结果中商品名称存入x集合，销量存入y集合
				for(Orders order : list) {
					x.add(order.getOrderMonth());
					y.add(order.getOrderPrice());
				}
				Map<String,Object> map = new HashMap<>();
				map.put("x", x);
				map.put("y",y);
				rv.setDataMap(map);
				
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("指定日期范围内的销量额查询失败！！！");
		return rv;
	}

}
