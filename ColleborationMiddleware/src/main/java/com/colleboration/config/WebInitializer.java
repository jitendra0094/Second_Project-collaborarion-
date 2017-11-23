package com.colleboration.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{DBConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{WebResolver.class};
	}


	@Override
	protected String[] getServletMappings() {
		
		return  new String[] {"/"};
	}

}
