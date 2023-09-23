package com.demo.cartapi.config.dev

import com.zaxxer.hikari.HikariDataSource

import net.bytebuddy.asm.Advice.Return

import javax.sql.DataSource

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.Scope
import org.springframework.core.env.Environment
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator
import org.springframework.jdbc.support.SQLExceptionTranslator
import org.springframework.orm.jpa.JpaDialect
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaDialect
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration(proxyBeanMethods=false)
@Profile(value="dev")
@PropertySource(value="classpath:/dbconfig/db-dev.properties" , ignoreResourceNotFound= false)
@EnableTransactionManagement
class DbPersistentDevConfig {

	Environment env
	DbPersistentDevConfig(Environment env) {
		this.env = env
	}

	@Bean(name="dataSource" , destroyMethod="close")
	@Primary
	DataSource dataSource() {
		//Using HikariCp connection pooling
		HikariDataSource dataSource = new HikariDataSource()
		dataSource.setAutoCommit(true) ;
		dataSource.setConnectionTimeout(60000)
		dataSource.setIdleTimeout(300000L)
		dataSource.setMinimumIdle(10)
		dataSource.setMaximumPoolSize(20)
		dataSource.setPoolName("simpleCartApiDevPool")
		dataSource.setDriverClassName(env.getProperty("h2.driver"))
		dataSource.setJdbcUrl(env.getProperty("h2.url"))
		dataSource.setUsername(env.getProperty("h2.user"))
		dataSource.setPassword(env.getProperty("h2.password"))
		dataSource.setSchema(env.getProperty("h2.schema"))
		dataSource.setTransactionIsolation("TRANSACTION_REPEATABLE_READ") ;
		
		return dataSource
	}
	
	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory( DataSource dataSource ) {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean()
		entityManagerFactoryBean.setPersistenceUnitName("simpleCartApiPersistenceUnit")
		entityManagerFactoryBean.setDataSource(dataSource)
		entityManagerFactoryBean.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class)
		entityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect())
		entityManagerFactoryBean.setPackagesToScan("com.demo.cartapi.entity")
		entityManagerFactoryBean.setJpaProperties(jpaProperties())
		
		//Jpa Vendor adapter
		 HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter()

		entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter)

		return entityManagerFactoryBean
	} 

	
	@Bean("transactionManager")
	@Primary
	public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject())
		//transactionManager.setDataSource(dataSource)
		return transactionManager;
	}

	Properties jpaProperties() {
		Properties props = new Properties()
		props.put("hibernate.dialect", env.getProperty("hibernate.dialect"))		
		props.put("hibernate.connection.isolation", env.getProperty("hibernate.connection.isolation"))
		props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"))
		props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"))
		props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"))
		//props.put("hibernate.cache.provider_class"  , env.getProperty("hibernate.cache.provider_class"))
		//props.put("cache.region.factory_class"  , env.getProperty("cache.region.factory_class"))
		props.put("hibernate.cache.region.factory_class"  , env.getProperty("hibernate.cache.region.factory_class"))
		props.put("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"))
		props.put("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"))
		props.put("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics"))

		return props
	}


	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
		return new PersistenceExceptionTranslationPostProcessor()
	}

	@Bean
	public SQLExceptionTranslator sqlExceptionTranslator() {
		return new SQLErrorCodeSQLExceptionTranslator();
	}
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource())
		jdbcTemplate.setDatabaseProductName("H2")
		jdbcTemplate.setExceptionTranslator(sqlExceptionTranslator())	
		
		return jdbcTemplate
		
	}
}
