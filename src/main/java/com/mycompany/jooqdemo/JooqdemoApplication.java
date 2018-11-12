package com.mycompany.jooqdemo;

import org.jooq.ExecuteListenerProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class JooqdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JooqdemoApplication.class, args);
	}
	
	// WHIT THIS CONFIGURATION ENABLED REST EXCEPTION HANDLER CATCH SPRING DATA ACCESS EXCEPTION
	// WORKS WITH SPRING BOOT 2.1 AND JOOQ 3.11.7
	@Bean
	@Order(Ordered.LOWEST_PRECEDENCE)
	public ExecuteListenerProvider customeListenerProvider() {
		return new DefaultExecuteListenerProvider(new ApplicationExceptionTranslator());
	}
	
	// WITH THIS CONFIGURATION ENABLED REST EXCEPTION HANDLER CATCH APPLICATION EXCEPTION ( BEHAVIOR EXPECTED )
//	@Bean
//	public static BeanPostProcessor jooqConfigurationPostProcessor() {
//		return new BeanPostProcessor() {
//
//			@Override
//			public Object postProcessBeforeInitialization(Object bean, String beanName)
//					throws BeansException {
//				return bean;
//			}
//
//			@Override
//			public Object postProcessAfterInitialization(Object bean, String beanName)
//					throws BeansException {
//				if (bean instanceof DefaultConfiguration) {
//					((DefaultConfiguration) bean).set(new ApplicationExceptionTranslator());
//				}
//				return bean;
//			}
//
//		};
//	}
}
