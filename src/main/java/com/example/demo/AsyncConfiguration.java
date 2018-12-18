package com.example.demo;

import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration extends WebMvcConfigurationSupport {

	private final static Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

	@Value("${corePoolSize:20}")
	private int corePoolSize;

	@Value("${maxPoolSize:40}")
	private int maxPoolSize;

	@Value("${queueCapacity:50}")
	private int queueCapacity;

	@Override
	public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(60 * 1000L);
		configurer.registerCallableInterceptors(timeoutInterceptor());
		configurer.setTaskExecutor(asyncServiceExecutor());
	}

	@Bean
	public TimeoutCallableProcessingInterceptor timeoutInterceptor() {
		return new TimeoutCallableProcessingInterceptor();
	}

	@Bean(name = "asyncServiceExecutor")
	public ThreadPoolTaskExecutor asyncServiceExecutor() {
		LOG.info("create asyncServiceExecutor");
		ThreadPoolTaskExecutor executor = new CustomizeThreadPoolTaskExecutor();
		// 配置核心线程数
		executor.setCorePoolSize(corePoolSize);
		// 配置最大线程数
		executor.setMaxPoolSize(maxPoolSize);
		// 配置队列大小
		executor.setQueueCapacity(queueCapacity);
		// 配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("async-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		// 执行初始化
		executor.initialize();
		return executor;
	}
	
	/*@Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }*/

    /* @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }*/

	/**
	 * 跨域资源共享(CORS)
	 * @param registry
	 */
	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		//设置允许跨域的路径
		registry.addMapping("/**")
				//设置允许跨域请求的域名
				.allowedOrigins("*")
				//是否允许证书 不再默认开启
				.allowCredentials(true)
				//设置允许的方法
				.allowedMethods("*")
				//跨域允许时间
				.maxAge(3600);
	}

}
