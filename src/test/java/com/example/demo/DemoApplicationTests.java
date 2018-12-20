package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.example.demo.service.AsyService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class DemoApplicationTests {

	/*
	private final static Logger LOG = LoggerFactory.getLogger(DemoApplicationTests.class);
	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	@Qualifier("com.example.demo.service.AsyService")
	AsyService asyService;

	@Test
	public void contextLoads() throws Exception {

		long start = System.currentTimeMillis();

		CompletableFuture<Person> future1 = asyService.aysSubmit("first one").exceptionally(fn -> {
			LOG.info("error: " + fn);
			return null;
		});
		CompletableFuture<Person> future2 = asyService.aysSubmit("first twotg").exceptionally(fn -> {
			LOG.info("error: " + fn);
			return null;
		});
		CompletableFuture<Person> future3 = asyService.aysSubmit("first three").exceptionally(fn -> {
			LOG.info("error: " + fn);
			return null;
		});

		Lists.newArrayList(new CompletableFuture<Person>());
		 
		// it block until all future is done.
		List<CompletableFuture<Person>> list = new ArrayList<CompletableFuture<Person>>();
		list.add(future1);
		list.add(future2);
		list.add(future3);
		// CompletableFuture<?> arr[]=new CompletableFuture<?>[list.size()];
		// CompletableFuture.allOf(list.toArray(arr)).join();
		List<Person> ps = sequence(list);
		LOG.info("start Time : " + start);
		for (int i = 0; i < ps.size(); i++) {
			Person p = ps.get(i);
			LOG.info(mapper.writeValueAsString(p));
		}
		LOG.info("end Time : " + (System.currentTimeMillis() - start));

		LOG.info("start to do other things----");
		long start1 = System.currentTimeMillis();
		Person future4 = asyService.aysSubmit1("first one1");
		Person future5 = asyService.aysSubmit1("first two2");
		Person future6 = asyService.aysSubmit1("first three3");
		LOG.info("start Time : " + start1);
		LOG.info(mapper.writeValueAsString(future4));
		LOG.info(mapper.writeValueAsString(future5));
		LOG.info(mapper.writeValueAsString(future6));
		LOG.info("end Time : " + (System.currentTimeMillis() - start1));
	}
*//*
	static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
		CompletableFuture<Void> allDoneFuture = CompletableFuture
				.allOf(futures.toArray(new CompletableFuture[futures.size()]));
		return allDoneFuture
				.thenApply(v -> futures.stream().map(future -> future.join()).collect(Collectors.<T>toList()));
	}*//*

	*//**
	 * 半异步操作
	 * @param futures
	 * @return
	 *//*
	static <T> List<T> sequence(List<CompletableFuture<T>> futures) {
		if (CollectionUtils.isEmpty(futures)) {
			return Collections.<T>emptyList();
		}
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
		return futures.stream().filter(future -> Optional.ofNullable(future).isPresent()).map(future -> {
			try {
				return future.get();
			} catch (InterruptedException | ExecutionException e) {
				return null;
			}
		}).collect(Collectors.<T>toList());
	}*/

}
