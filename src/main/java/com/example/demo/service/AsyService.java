package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

import com.example.demo.Person;

public interface AsyService {
	
	public CompletableFuture<Person> aysSubmit(String msg) throws Exception;
	
	public Person aysSubmit1(String msg);

}
