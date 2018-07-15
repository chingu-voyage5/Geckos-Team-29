package com.geckos.springmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	System.out.println("Provide configuration classes for Root App");
    	return new Class<?>[] { DbConfig.class,SecurityConfig.class};
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
    	System.out.println("Provide configuration classes for Web App Context");
    	return new Class<?>[] { SpringConfig.class};
    }
  
    @Override
    protected String[] getServletMappings() {
    	System.out.println("Provide URLs to which Dispatcher Servlet Should be mapped to");
        return new String[] { "/" };
    }
}
