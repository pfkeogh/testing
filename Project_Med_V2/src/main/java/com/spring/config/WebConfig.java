package com.spring.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.spring.pojo.Appointment;
import com.spring.pojo.Doctor;
import com.spring.pojo.Leave;
import com.spring.pojo.Review;
import com.spring.pojo.Schedule;
import com.spring.pojo.User;

@Configuration
@ComponentScan(basePackages = { "com.spring" })
@EnableTransactionManagement
//@PropertySource("classpath:database.properties")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{
	@Override
	public void configureDefaultServletHandling( //for static page handling
	DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {//required for Apache tiles
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] {"/WEB-INF/layout/tiles.xml"});//declare views here
		tiles.setCheckRefresh(true);
		return tiles;
	}
	
	@Bean
	public ViewResolver viewResolver() {//view resolver for tiles resolver
		return new TilesViewResolver();
	}
 
 @Bean
    public DataSource dataSource() {//config for DB
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:patsdb");
        dataSource.setUsername("C##PATRICK");
        dataSource.setPassword("madcat");
         
        return dataSource;
    }
 
 @Bean 
 public LocalSessionFactoryBean sessionFactory(){//Hibernate Bootstrap, uses datasource
  Properties hibernateProperties = new Properties();
  hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
  hibernateProperties.put("hibernate.show_sql", "true");
  hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
  //hibernateProperties.put("hibernate.enable_lazy_load_no_trans", "true");
  
  LocalSessionFactoryBean bean=new LocalSessionFactoryBean();
  bean.setDataSource(dataSource());
  bean.setHibernateProperties(hibernateProperties);
  bean.setAnnotatedClasses(new Class<?>[] { User.class, Doctor.class, Leave.class, Appointment.class,
	  Review.class, Schedule.class });
  //bean.setAnnotatedPackages("com.spring.pojo");
  
  return bean;
 }
 
 @Bean//Spring class that manages messy details of opening, closing hibernate transactions
 public HibernateTransactionManager transactionManager(){
  HibernateTransactionManager manager=new HibernateTransactionManager();
  manager.setSessionFactory(sessionFactory().getObject());
  
  return manager;
 }
 

}