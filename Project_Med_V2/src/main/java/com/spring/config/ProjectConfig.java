package com.spring.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.
AbstractAnnotationConfigDispatcherServletInitializer;

@Order(1)
public class ProjectConfig
	extends AbstractAnnotationConfigDispatcherServletInitializer {//Automatically configures dispatcher servlet and the spring app context
	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {  RootConfig.class };
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}
	/*@Override
	protected void customizeRegistration(Dynamic registration) {
	registration.setMultipartConfig(
			new MultipartConfigElement("/temp/uploads", 2097152, 4194304, 0));//files no larger than 2MB, no larger than 4MB total, written to disk
	}*/
}