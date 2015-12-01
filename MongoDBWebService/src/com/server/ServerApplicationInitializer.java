package com.server;

import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

class ServerApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{ };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{ };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{ "/" };
	}
	
}
