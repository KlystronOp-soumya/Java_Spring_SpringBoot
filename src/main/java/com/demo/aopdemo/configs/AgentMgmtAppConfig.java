package com.demo.aopdemo.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;

@Configuration(proxyBeanMethods = false)
@Import(value = {AgentMgmtRedisConfig.class})
@EntityScan(basePackages = {"com.demo.aopdemo.entity"})
@EnableCaching
public class AgentMgmtAppConfig {
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean("transactionManager")
	@Primary
	public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
		return transactionManager;
	}
	
}
