package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.service.UsersService;
import com.springcloud.vo.ResultValure;

/**
 * 控制层: 接受视图层的数据，并调用模型层相应的方法，将数据返回到视图层中
 * 
 * @author 曹江柳
 *
 */
@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	/**
	 * 用户登录
	 * 
	 * @param userId       用户的编号
	 * @param userPassword 用户的密码
	 * @param jdictionId   用户的权限编号
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ResultValure login(@RequestParam("userId") Integer userId, @RequestParam("userPassword") String userPassword,
			@RequestParam("jdictionId") Integer jdictionId) {
		ResultValure rv = new ResultValure();

		try {
			Users login = this.usersService.login(userId, userPassword, jdictionId);
			if (login != null) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				map.put("loginUser", login);
				rv.setDataMap(map);

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("登录信息不正确，请重新输入！！！");
		return rv;
	}

	@RequestMapping(value = "/insert")
	public ResultValure insert(Users users) {
		ResultValure rv = new ResultValure();
		try {
			Users insert = this.usersService.insert(users);
			if (insert != null) {
				rv.setCode(0);
				rv.setMessage("用户录入成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("用户录入失败！！！");
		return rv;
	}

	/**
	 * 查询满足条件的用户信息
	 * 
	 * @param users      用户信息
	 * @param pageNumber 页数
	 * @return
	 */
	@RequestMapping(value = "/select")
	public ResultValure select(Users users, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValure rv = new ResultValure();

		try {
			Page<Users> page = this.usersService.select(users, pageNumber);
			// 获得分页的数据
			List<Users> list = page.getContent();
			// 判断是否查询到数据
			if (list != null && list.size() > 0) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				// 将分页的数据添加到map中
				map.put("userList", list);

				PageUtils pageUtils = new PageUtils((int) page.getTotalElements());
				pageUtils.setPageNumber(pageNumber);
				// 将分页的信息也添加到Map中
				map.put("pageUtils", pageUtils);

				// 将Map添加到ResultValue中
				rv.setDataMap(map);

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		return rv;
	}

	/**
	 * 修改指定编号的用户状态
	 * 
	 * @param userId     用户编号
	 * @param userStatus 用户状态
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateStatus")
	public ResultValure updateStatus(@RequestParam("userId") Integer userId,
			@RequestParam("userStatus") Integer userStatus) {
		ResultValure rv = new ResultValure();

		try {
			Integer status = this.usersService.updateStatus(userId, userStatus);
			if (status > 0) {
				rv.setCode(0);
				rv.setMessage("用户状态修改成功！！！");
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("用户状态修改失败！！！");
		return rv;
	}

	/**
	 * 查询指定编号的用户信息
	 * 
	 * @param userId 用户编号
	 * @return
	 */
	@RequestMapping(value = "/select/{userId}")
	public ResultValure selectById(@PathVariable("userId") Integer userId) {
		ResultValure rv = new ResultValure();

		try {
			// 调用service中的方法，并获得查询结果
			Users users = this.usersService.selectById(userId);
			// 如果成功
			if (users != null) {
				// 设置结果的状态为0
				rv.setCode(0);
				// 创建Map集合保存到查询结果
				Map<String, Object> map = new HashMap<>();
				// 将查询结果保存到Map集合中
				map.put("users", users);
				// 将Map集合添加到ResultValue对象中
				rv.setDataMap(map);
				// 返回ResultValue对象
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("获取用户信息失败！！！");
		return rv;
	}

	/**
	 * 修改指定用户的信息
	 * 
	 * @param users 修改的用户信息
	 * @return
	 */
	@RequestMapping(value = "/update")
	public ResultValure update(Users users) {
		ResultValure rv = new ResultValure();

		try {
			// 调用service中相应的方法修改用户信息，并获得修改是否成功
			Integer update = this.usersService.update(users);
			// 如果修改成功
			if (update > 0) {
				// 设置结果的状态为0
				rv.setCode(0);
				// 返回ResultValue对象
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("用户信息修改失败！！！");
		return rv;

	}

	/**
	 * 根据参数修改指定编号的用户头像或用户昵称或用户密码
	 * 
	 * @param users 修改用户信息
	 * @return
	 */
	@RequestMapping(value = "/updateMessage")
	public ResultValure updateMessage(Users users) {
		ResultValure rv = new ResultValure();

		try {
			Integer message = this.usersService.updateMessage(users);
			if (message > 0) {
				rv.setCode(0);
				rv.setMessage("用户信息修改成功！！！");
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户信息修改失败！！！");
		return rv;
	}

}
