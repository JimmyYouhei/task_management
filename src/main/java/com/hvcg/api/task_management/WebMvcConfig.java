package com.hvcg.api.task_management;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 
 * this class is to config WebMVC
 * 
 * Currently this is used to add global CORS mapping to avoid CORS problem to the Front-End
 * 
 * @author JY
 *
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
        registry
        .addMapping("/**")
        .allowedMethods("*")
        .allowedHeaders("*")
        .allowedOrigins("*")
        .allowCredentials(true);
	}
	
	

}
