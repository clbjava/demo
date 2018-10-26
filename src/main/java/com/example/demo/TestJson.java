package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.core.io.FileSystemResource;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TestJson {
	public static void main(String[] args) throws JsonProcessingException {
		/*ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aa", "test");
		map.put("bb", Boolean.FALSE);
		map.put("cc", null);
		System.out.println(mapper.writeValueAsString(map));
		User user = new User();
		user.setIsFlag(Boolean.FALSE);
		System.out.println(mapper.writeValueAsString(user));*/
		
		try {
			URL url=new URL("http", "localhost", 8070, "/transactionServer/query/hello world");
			HttpURLConnection con=(HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			//con.connect();
			System.out.println(con.getURL());
			InputStream input=con.getInputStream();
			byte[] btyes=new byte[1024];
			
			while(input.read(btyes)<0) {
				input.close();
			}
			
			FileSystemResource file =new FileSystemResource("D:/dir1/");
			if(!file.exists()) {
				file.createRelative("D:/dir1/");
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
