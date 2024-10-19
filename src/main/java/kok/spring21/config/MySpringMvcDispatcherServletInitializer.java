package kok.spring21.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import javax.servlet.Filter;

import kok.spring21.myFilter;

//для настройки диспетчер-сервлета java-кодом(замена web.xml):
class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
    protected Class<?>[] getRootConfigClasses(){
		return new Class[]{PersistenceConfig.class};
    }
	@Override
    protected Class<?>[] getServletConfigClasses(){
		return new Class[]{WebConfig.class};
    }
	@Override
    protected String[] getServletMappings(){
		return new String[] {"/"};
    }

	@Override
    protected Filter[] getServletFilters(){
		return new Filter[] {new HiddenHttpMethodFilter(),new myFilter()};
    }
}
