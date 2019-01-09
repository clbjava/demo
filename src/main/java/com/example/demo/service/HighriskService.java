package com.example.demo.service;

import com.example.demo.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface HighriskService {
	
	
	User highrisk(User user, String req) throws JsonProcessingException;
	
	String highrisk1(String req) throws JsonProcessingException;

}
