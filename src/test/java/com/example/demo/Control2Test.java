package com.example.demo;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Control2Test extends DemoApplicationTests{
	
	private final static Logger LOG = LoggerFactory.getLogger(DemoApplicationTests.class);
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void testGetMap() throws Exception {
		this.mvc.perform(get("/getMap")).andExpect(status().isOk())
		.andExpect(content().string("{\"data\":[{\"phone\":\"15111680080\",\"contactName\":\"!\",\"companyName\":\"world\",\"customerId\":\"hello\"}]}"));
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testPost() {
		fail("Not yet implemented");
	}

}
