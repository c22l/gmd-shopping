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
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;
import com.springcloud.vo.ResultValure;

/**
 * 商品模块的控制层
 * 
 * @author 曹江柳
 *
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "/insert")
	public ResultValure insert(Goods goods) {
		ResultValure rv = new ResultValure();

		try {
			Integer insert = this.goodsService.insert(goods);
			if (insert > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息录入成功！！！");
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("商品信息录入失败！！！");
		return rv;
	}

	/**
	 * 查询满足条件的商品信息
	 * 
	 * @param goods      查询条件
	 * @param pageNumber 页数
	 * @return
	 */
	@RequestMapping(value = "/select")
	public ResultValure select(Goods goods, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValure rv = new ResultValure();

		try {
			// 查询满足条件的商品信息
			PageInfo<Goods> pageInfo = this.goodsService.select(goods, pageNumber);
			// 从分页信息中获得商品的数据
			List<Goods> list = pageInfo.getList();
			// 如果查询到了满足条件的商品信息
			if (list != null && list.size() > 0) {
				rv.setCode(0);

				// 创建Map集合保存到查询结果
				Map<String, Object> map = new HashMap<>();
				// 将查询结果保存到Map集合中
				map.put("goodsList", list);

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
		rv.setMessage("没有找到满足条件的商品信息！！！");
		return rv;
	}

	/**
	 * 修改商品的信息(热卖，新品，状态，图片)
	 * 
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/updateById")
	public ResultValure updateById(Goods goods) {
		ResultValure rv = new ResultValure();

		try {
			Integer update = this.goodsService.updateGoodsById(goods);
			if (update > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息修改成功！！！");
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("商品信息修改失败！！！");
		return rv;
	}

	/**
	 * 修改指定编号的商品信息
	 * 
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/update")
	public ResultValure update(Goods goods) {
		ResultValure rv = new ResultValure();

		try {
			Integer update = this.goodsService.update(goods);
			if (update > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息修改成功！！！");
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("商品信息修改失败！！！");
		return rv;

	}

	/**
	 * 查询销量前10的商品信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectGroup")
	public ResultValure selectGroup() {
		ResultValure rv = new ResultValure();

		try {
			List<Goods> list = this.goodsService.selectGroup();
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				// 创建两个集合，用于保存柱状图x抽与y抽的数据
				List<String> x = new ArrayList<>();
				List<Integer> y = new ArrayList<>();
				//将查询结果中商品名称存入x集合，销量存入y集合
				for(Goods goods : list) {
					x.add(goods.getGoodsName());
					y.add(goods.getGoodsSum());
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
		rv.setMessage("查询销量前10的商品信息失败！！！");
		return rv;
	}

}
