package com.example.demo;

import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.SimpleIdGenerator;

public class Test {

	public static void main(String[] args) {
		/*
		 * FileSystemResource file =new FileSystemResource("D://dir1");
		 * if(!file.exists()) { file.createRelative("D://dir1"); }
		 */

		/*UriComponents uriComponents = UriComponentsBuilder
				.fromUriString("http://example.com/hotels/{hotel}/bookings/{booking}").build().expand("中国——", "21")
				.encode();
		URI uri = uriComponents.toUri();
		
		System.out.println(uri);*/
		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println(Test.class.getName());
		
		/*FileSystemResource resource=new FileSystemResource("D:\\Value\\table\\in.html");		
		try {
		System.out.println(new String(FileCopyUtils.copyToByteArray(resource.getFile())));
		FileSystemUtils.deleteRecursively(resource.getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		SimpleIdGenerator id=new SimpleIdGenerator();
		System.out.println(id.generateId());
		System.out.println(id.generateId());
		System.out.println(id.generateId());
		System.out.println(id.generateId());
	}

}
