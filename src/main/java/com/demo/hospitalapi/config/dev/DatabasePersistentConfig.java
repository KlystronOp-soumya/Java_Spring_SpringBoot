package com.demo.hospitalapi.config.dev;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;

import javafx.scene.web.WebView;

@Configuration(proxyBeanMethods = false)
@Profile("dev")
@PropertySource(ignoreResourceNotFound = false, value = "classpath:dbconfig/db-dev.properties")
@EnableTransactionManagement
public class DatabasePersistentConfig {

	@Autowired
	private Environment env;
	// Use in-file H2 database

	@Bean("dataSource")
	@Primary
	public DataSource dataSource() {

		final HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setAutoCommit(true);
		hikariDataSource.setDriverClassName(env.getProperty("h2.driver"));
		hikariDataSource.setJdbcUrl(env.getProperty("h2.jdbc_url"));
		hikariDataSource.setUsername(env.getProperty("h2.user"));
		hikariDataSource.setPassword(env.getProperty("h2.password"));

		hikariDataSource.setPoolName("HospitalApiConnectionPool");
		hikariDataSource.setMaximumPoolSize(15);
		hikariDataSource.setSchema("HOSPITALDB");
		hikariDataSource.setMinimumIdle(5);
		hikariDataSource.setIdleTimeout(30000L);
		return hikariDataSource;
		// return
		// DataSourceBuilder.create().driverClassName(env.getProperty("h2.driver")).url(env.getProperty("h2.jdbc-url")).username(env.getProperty("h2.user")).password(env.getProperty("h2.password")).build()
		// ;
	}

	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPersistenceUnitName("HospitalApiPersistentUnit");
		entityManagerFactoryBean.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan("com.demo.hospitalapi.domain");
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaProperties(jpaProperties());
		final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

		return entityManagerFactoryBean;

	}
//Application fails when this bean is injected
	/*
	 * @Bean public PersistenceAnnotationBeanPostProcessor
	 * persistenceAnnotationBeanPostProcessor() { return new
	 * PersistenceAnnotationBeanPostProcessor(); }
	 */

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	// added on 26/02/2023

	@Bean("jpaDialect")
	public JpaDialect jpaDialect() {
		JpaDialect jpaDialect = new HibernateJpaDialect();
		return jpaDialect;
	}

	@Bean("transactionManager")
	@Primary
	public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
		return transactionManager;
	}

	@Bean(name = "jdbcTemplateBean")
	public JdbcTemplate jdbcTemplate() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		jdbcTemplate.setDatabaseProductName("H2");
		jdbcTemplate.setExceptionTranslator(sqlExceptionTranslator());
		return jdbcTemplate;
	}

	@Bean
	public SQLExceptionTranslator sqlExceptionTranslator() {
		return new SQLErrorCodeSQLExceptionTranslator();
	}

	protected Properties jpaProperties() {
		final Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		// props.put("hibernate.connection.provider_class",
		// "com.zaxxer.hikari.hibernate.HikariConnectionProvider");
		props.put("hibernate.connection.isolation", "REPEATABLE_READ");
		props.put("hibernate.show_sql", true);
		props.put("hibernate.format_sql", true);
		props.put("hibernate.hbm2ddl.auto", "update");
		props.put("hibernate.generate_statistics", true);

		return props;

	}

}
