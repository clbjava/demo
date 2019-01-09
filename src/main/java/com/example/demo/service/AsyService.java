package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

import com.example.demo.Person;

public interface AsyService {
	
	CompletableFuture<Person> aysSubmit(String msg) throws Exception;
	
	Person aysSubmit1(String msg);

}
