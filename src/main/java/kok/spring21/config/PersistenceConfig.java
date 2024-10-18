package kok.spring21.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.core.env.Environment;

import org.springframework.transaction.*;
import org.springframework.jdbc.*;

import org.springframework.jdbc.datasource.*;

import javax.sql.DataSource;

import java.util.Properties;


import org.hibernate.*;



//настройка Hibernate
@Configuration
@ComponentScan("kok.spring21")           //skanirovanie proishodit takzhe vo vlozhennyh papkah rekursivno
@EnableTransactionManagement
public class PersistenceConfig{

	private final Environment env;
	private final ApplicationContext ac;

        Session session;
	
	@Autowired
	public PersistenceConfig(ApplicationContext c,Environment env){ac=c;this.env=env;}


	@Bean
        SessionFactory sessionFactory() throws Exception{
                System.out.println(">>SF-0");

		SessionFactory sessionFactory=new org.hibernate.cfg.Configuration()
                   .configure()
                   .addPackage("kok.spring21.models")
                   .buildSessionFactory();		

                System.out.println(">>SF-5"+sessionFactory.toString());

                return sessionFactory;
        }
}
