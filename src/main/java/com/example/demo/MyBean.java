package com.example.demo;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MyBean {
	
	private final static Logger LOG = LoggerFactory.getLogger(MyBean.class);
	private final ObjectMapper mapper = new ObjectMapper();

	
	@Autowired
	public MyBean(ApplicationArguments args) throws JsonProcessingException {
		boolean debug = args.containsOption("debug");
		List<String> files = args.getNonOptionArgs();
		// if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
		
		LOG.info("--------------------:{}",mapper.writeValueAsString(files));
		
		String[] dd = args.getSourceArgs();
		LOG.info("--------------------:{}",mapper.writeValueAsString(dd));
		Set<String> sss=args.getOptionNames();
		LOG.info("--------------------:{}",mapper.writeValueAsString(sss));
	}

}
