package com.example.demo.service.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.Person;
import com.example.demo.service.AsyService;

@Service("com.example.demo.service.AsyService")
public class AsyServiceImpl implements AsyService {
	private final static Logger LOG = LoggerFactory.getLogger(AsyServiceImpl.class);

	@Override
	@Async("asyncServiceExecutor")
	public CompletableFuture<Person> aysSubmit(String msg) throws Exception {
		LOG.info("Async:{} ",Thread.currentThread().getName());
		try {
			/*if("first two".equals(msg)) {
				Thread.sleep(1500);
				}*/
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if("first twotg".equals(msg)) {
			throw new Exception("fffff");
		}
		
		Person person=new Person();
		person.setRetMsg(msg);
		return CompletableFuture.completedFuture(person);
	}

	

	@Override
	public Person aysSubmit1(String msg) {
		LOG.info("syn: {}",Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		Person person=new Person();
		person.setRetMsg(msg);
		return person;
	}
	
}
