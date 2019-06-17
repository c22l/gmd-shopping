package com.springcloud.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springcloud.common.UploadUtils;
import com.springcloud.vo.ResultValure;

@RestController
public class FileUploadController {
	// 从application.properties文件中获得指定键的值，并赋值相应的成员变量
	@Value("${web.user-path}")
	private String userPath;

	@Value("${web.goods-path}")
	private String goodsPath;

	@RequestMapping(value = "/userUpload")
	public ResultValure userUpload(@RequestParam("userHphoto") MultipartFile file) {
		ResultValure rv = new ResultValure();
		try {
			Map<String, Object> map = this.uploadFile(userPath, file);
			if (map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户头像上传失败!!!");
		return rv;
		// 以上try{...}catch{...}内的代码与下面注释的代码效果一样
		/*
		 * // 获得新的文件名 String fileName = UploadUtils.getFileName(); // 根据上传文件的文件名的文件扩展名
		 * String extendedName =
		 * UploadUtils.getExtendedName(file.getOriginalFilename());
		 * 
		 * // 上传文件 try { // 1、将文件转换为自字节类型的数组 byte[] bytes = file.getBytes(); //
		 * 2、创建File类的对象，并设置文件的上传路径及文件名 File saveFile = new File(userPath + fileName +
		 * extendedName); // 3、上传文件 FileCopyUtils.copy(bytes, saveFile);
		 * 
		 * rv.setCode(0); // 当文件上传成功时，将文件新的文件名与扩展名传递回视图层 Map<String,Object> map = new
		 * HashMap<>(); map.put("fileName", fileName); map.put("extendedName",
		 * extendedName); rv.setDataMap(map);
		 * 
		 * return rv; } catch (IOException e) { e.printStackTrace(); } rv.setCode(1);
		 * rv.setMessage("用户头像上传失败!!!"); return rv;
		 */
	}

	@RequestMapping(value = "/goodsUpload")
	public ResultValure goodsUpload(@RequestParam("goodsPhoto") MultipartFile file) {
		ResultValure rv = new ResultValure();
		try {
			Map<String, Object> map = this.uploadFile(goodsPath, file);
			if (map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品图片上传失败!!!");
		return rv;
	}

	/**
	 * 删除商品的商品图片
	 * 
	 * @param goodsPhoto 商品图片
	 * @return
	 */
	@RequestMapping(value = "/deleteGoodsPhoto")
	public ResultValure deleteGoodsPhoto(@RequestParam("goodsPhoto") String goodsPhoto) {
		ResultValure rv = new ResultValure();

		try {
			// 从URL中获得商品图片的名字
			int indexOf = goodsPhoto.lastIndexOf("/");
			if (indexOf != -1) {
				String fileName = goodsPhoto.substring(indexOf + 1);
				// System.out.println(fileName);
				File file = new File(this.goodsPath + fileName);
				// 判断文件是否存在
				file.delete();
				rv.setCode(0);
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品图片删除失败！！！");
		return rv;
	}

	/**
	 * 删除用户头像
	 * 
	 * @param userHphoto 用户头像
	 * @return
	 */
	@RequestMapping(value = "/deleteUserHphoto")
	public ResultValure deleteUserHphoto(@RequestParam("userHphoto") String userHphoto) {
		ResultValure rv = new ResultValure();

		try {
			// 从URL中获得用户头像的名字
			int indexOf = userHphoto.lastIndexOf("/");
			if (indexOf != -1) {
				String fileName = userHphoto.substring(indexOf + 1);
				// System.out.println(fileName);
				File file = new File(this.userPath + fileName);
				// 判断文件是否存在
				file.delete();
				rv.setCode(0);
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户头像删除失败！！！");
		return rv;
	}

	/**
	 * 上传文件
	 * 
	 * @param path 上传文件的路径
	 * @param file 上传的文件
	 * @return 成功返回java.util.Map类型的实例，否则返回null
	 * @throws IOException
	 */
	private Map<String, Object> uploadFile(String path, MultipartFile file) throws IOException {
		// 获得新的文件名
		String fileName = UploadUtils.getFileName();

		// 根据上传文件的文件名的文件扩展名
		String extendedName = UploadUtils.getExtendedName(file.getOriginalFilename());

		// 上传文件
		// 1、将文件转换为自字节类型的数组
		byte[] bytes = file.getBytes();
		// 2、创建File类的对象，并设置文件的上传路径及文件名
		File saveFile = new File(path + fileName + extendedName);
		// 3、上传文件
		FileCopyUtils.copy(bytes, saveFile);

		// 当文件上传成功时，将文件新的文件名与扩展名传递回视图层
		Map<String, Object> map = new HashMap<>();
		map.put("fileName", fileName);
		map.put("extendedName", extendedName);
		return map;
	}

}
