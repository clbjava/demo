package com.example.demo.service.impl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.demo.DemoApplicationTests;
import com.example.demo.User;
import com.example.demo.service.HighriskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HighriskServiceImplTest extends DemoApplicationTests {

	private final static Logger LOG = LoggerFactory.getLogger(HighriskServiceImplTest.class);
	private final ObjectMapper mapper = new ObjectMapper();
	

	@Autowired
	@Qualifier("com.example.demo.service.HighriskService")
	HighriskService highriskService;

	@Test
	public void testHighrisk() throws JsonProcessingException {
		User user = new User();
		user.setAge("20");
		String req = "abc";

		LOG.info("back ={}",mapper.writeValueAsString( highriskService.highrisk(user, req)));
		/*user.setAge("21");
		req = "abc";
		LOG.info("back ;{}",mapper.writeValueAsString( highriskService.highrisk(user, req)));*/
		LOG.info("back1 ={}", highriskService.highrisk1(req));
	}

}
