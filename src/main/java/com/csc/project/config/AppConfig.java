package com.csc.project.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
	@Configuration
	@EnableAsync
	public class AppConfig {

	    @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
	    }

	    @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOriginPattern("*"); // Allow all origins
	        config.addAllowedHeader("*"); // Allow all headers
	        config.addAllowedMethod("*"); // Allow all methods (GET, POST, PUT, DELETE, etc.)

	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }

	    @Bean(value = "logExecutor")
	    public Executor taskExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(5);
	        executor.setMaxPoolSize(10);
	        executor.setQueueCapacity(100);
	        executor.setThreadNamePrefix("LogWriter-");
	        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	        executor.initialize();
	        return executor;
	    }

}
