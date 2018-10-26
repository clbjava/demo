package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Control2 {
	private final static Logger LOG = LoggerFactory.getLogger(Control2.class);
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "/getMap", method = RequestMethod.GET)
	public Map<String,List<Map<String,Object>>> getMap(HttpServletRequest request, HttpServletResponse response) {
		Map<String,List<Map<String,Object>>> result=new HashMap<String,List<Map<String,Object>>>();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		list.add(map);
		map.put("customerId","hello");
		map.put("companyName","world");
		map.put("contactName","!");
		map.put("phone","15111680080");
		result.put("data", list);
		 return result;
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<Map<String,Object>>get(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
		Thread.sleep(5000);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		list.add(map);
		map.put("customerId","hello");
		map.put("companyName","world");
		map.put("contactName","!");
		map.put("phone","15111680080");
		 return list;
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public User post(@RequestBody User  user,
			HttpServletRequest request, HttpServletResponse response) {
		/*response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with"); */
		if(user!=null)
        System.out.println("===="+user.getName()+"--"+user.getAge());
		
		return user;
	}

	@RequestMapping(value = "/asyget", method = RequestMethod.GET)
	public WebAsyncTask<Map<String,Object>> asyget(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
		// 打印处理线程名
		LOG.info("The main Thread name is " + Thread.currentThread().getName());
		//response.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html;charset=UTF-8");
	    // 此处模拟开启一个异步任务,超时时间为10s
	    WebAsyncTask<Map<String,Object>> task1 = new WebAsyncTask<Map<String,Object>>(1 * 1000L, () -> {
	    	LOG.info("The first Thread name is " + Thread.currentThread().getName());
	    	// 任务处理时间5s,不超时
	    	try {
		    	Thread.sleep(2 * 1000L);
			} catch (Exception e) {
				LOG.info("sleep： " + Thread.currentThread().getName());
			}
	    	//return new String("任务1顺利执行成功！任何异常都没有抛出！".getBytes(), "ISO-8859-1");
	    	
	    	Map<String,Object> map=new HashMap<String,Object>();
			map.put("customerId","任务1顺利执行成功！任何异常都没有抛出！");
			map.put("companyName","world");
			map.put("contactName","!");
			map.put("phone","15111680080");
	    	return map;
	    });
	    
	    // 任务执行完成时调用该方法
	   /* task1.onCompletion(() -> {
	    	LOG.info("任务1执行完成啦！");
	    });*/
	    
	    task1.onTimeout(new Callable<Map<String,Object>>() {
			@Override
			public Map<String,Object> call()  {
				LOG.info(Thread.currentThread().getName() + " onTimeout");
				// 超时的时候，直接抛异常，让外层统一处理超时异常
				//throw new TimeoutException("调用超时");
				
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("code","0001");
				map.put("msg","request time out!");
				
		    	return map;
			}});
	    
	    LOG.info("task1继续处理其他事情！");
	    return task1;
	}
}
