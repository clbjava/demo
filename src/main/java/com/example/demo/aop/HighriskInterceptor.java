package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Service("com.example.demo.aop.HighriskInterceptor")
public class HighriskInterceptor {
	
	private final static Logger LOG=LoggerFactory.getLogger(HighriskInterceptor.class);
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Around("@annotation(com.example.demo.aop.Highrisk)")
	public Object doAround(ProceedingJoinPoint point) throws Throwable {
		
		LOG.info("doAround:{}",mapper.writeValueAsString(point.getArgs()));
		Object args[]=point.getArgs();
		if(args[0] instanceof User) {
			User req=(User)args[0];
			if(req.getAge().equals("20")) {
			  // User person=new User();
				//person.setFlag("01");
				//person.setRetCode("01");
			   //return person;
				req.setAge("300");
				LOG.info("---:{}",mapper.writeValueAsString(args[0]));
				return  req;
			}
			
			
		}
		
		if(args[0] instanceof String) {
			String req=(String)args[0];
			if("abc".equalsIgnoreCase(req)) {
				req="123456";
				LOG.info("---:{}",mapper.writeValueAsString(args[0]));
				return  req;
			}
		}
		
		
		return point.proceed();
	
	}
	
	/*@Before("@annotation(com.example.demo.aop.Highrisk)")
	public Object doAround(JoinPoint  point) throws Throwable {
		
		LOG.info("doAround:{}",mapper.writeValueAsString(point.getArgs()));
		Object args[]=point.getArgs();
		User req=(User)args[0];
		if(req.getAge().equals("20")) {
		   Map<String,Object> map=new HashMap<String,Object>();
		   map.put("a", "aaaa");
		   map.put("retCode", "code");
		   map.put("retMsg", "msg");
			Person person=new Person();
			person.setFlag("01");
		   return person;
		}
		return null;
	}*/

}
