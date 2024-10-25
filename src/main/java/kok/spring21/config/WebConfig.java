package kok.spring21.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

import org.springframework.context.annotation.*;

import org.springframework.core.env.Environment;


//настройка template engine для сервлета:
@Configuration
@ComponentScan("kok.spring21")           //skanirovanie proishodit takzhe vo vlozhennyh papkah rekursivno
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	private final Environment env;
	private final ApplicationContext ac;
	
	@Autowired
	public WebConfig(ApplicationContext c,Environment env){ac=c;this.env=env;}

	@Bean
	public SpringResourceTemplateResolver templateResolver(){
		SpringResourceTemplateResolver tr=new SpringResourceTemplateResolver();
		tr.setApplicationContext(ac);
		tr.setPrefix("WEB-INF/views/");
		tr.setSuffix(".html");
                tr.setCharacterEncoding("UTF-8");
		return tr;
	}	

	@Bean
	public SpringTemplateEngine templateEngine(){
		SpringTemplateEngine te=new SpringTemplateEngine();
		te.setTemplateResolver(templateResolver());
		te.setEnableSpringELCompiler(true);
		return te;
	}
	@Override
	public void configureViewResolvers(ViewResolverRegistry r){
		ThymeleafViewResolver vr=new ThymeleafViewResolver();
		vr.setTemplateEngine(templateEngine());
                vr.setCharacterEncoding("UTF-8"); 
                vr.setForceContentType(true); // <- this was added
                vr.setContentType("text/html; charset=UTF-8"); // <- this was added
		r.viewResolver(vr);
	}

}
