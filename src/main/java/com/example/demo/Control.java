package com.example.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Control {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				// 这里只是简单例子，文件直接输出到项目路径下。
				// 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
				// 还有关于文件格式限制、文件大小限制，详见：中配置。
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(new File(file.getOriginalFilename())));
				out.write(file.getBytes());
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			}
			return "上传成功";
		} else {
			return "上传失败，因为文件是空的.";
		}
	}

	@RequestMapping(value = "/upload/batch", method = RequestMethod.POST)
	public @ResponseBody String batchUpload(@RequestParam("file") MultipartFile[] file,
			@RequestParam("file1") MultipartFile file1,HttpServletRequest request) throws UnsupportedEncodingException, IOException {
		
		System.out.println(file.length);
		for(MultipartFile index:file) {
			System.out.println(index.getOriginalFilename()+"---"+index.getName());
			System.out.println(index.getOriginalFilename()+(new String(index.getBytes(),"UTF-8")));
		}
		System.out.println(file1.getOriginalFilename()+"--ff-"+file1.getName());
		System.out.println((String)request.getParameter("tx"));
		return "Y";
	}

}
