package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.User;
import com.example.demo.aop.Highrisk;
import com.example.demo.aop.HighriskInterceptor;
import com.example.demo.service.HighriskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("com.example.demo.service.HighriskService")
public class HighriskServiceImpl implements HighriskService {

	private final static Logger LOG=LoggerFactory.getLogger(HighriskInterceptor.class);
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	@Highrisk
	public User highrisk(User user, String req) throws JsonProcessingException {
		LOG.info("highrisk:{}",mapper.writeValueAsString(user));

		// TODO Auto-generated method stub
		User user1=new User();
		user1.setAge("12");
		user1.setName("ddd");
		return user1;
	}
	
	
	@Override
	@Highrisk
	public String highrisk1(String req) throws JsonProcessingException {
		LOG.info("highrisk:{}",req);

		// TODO Auto-generated method stub
		
		return "hello world!";
	}

}
