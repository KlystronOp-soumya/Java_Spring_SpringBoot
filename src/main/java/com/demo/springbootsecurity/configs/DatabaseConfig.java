package com.demo.springbootsecurity.configs;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource(value = "classpath:db-config.properties")
public class DatabaseConfig {

	private Environment env;

	public DatabaseConfig(final Environment environment) {
		this.env = environment ;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		
		return jpaTransactionManager;
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		//entityManagerFactoryBean.setPersistenceUnitName("");
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan("com.demo.springbootsecurity.entities");
		final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		
		//use this program based settings in case of program based configuration
		entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setShowSql(true);
		entityManagerFactoryBean.afterPropertiesSet();
		return entityManagerFactoryBean;
	}

	@Bean
	public DataSource dataSource() {

		final HikariDataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}

	protected HikariConfig hikariConfig() {

		final HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("h2.driver"));
		hikariConfig.setJdbcUrl(env.getProperty("h2.url"));
		hikariConfig.setUsername(env.getProperty("h2.user"));
		hikariConfig.setPassword(env.getProperty("h2.password"));
		hikariConfig.setConnectionTimeout(50000);
		hikariConfig.setIdleTimeout(300000);
		hikariConfig.setMaxLifetime(900000);
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(10);
		hikariConfig.setTransactionIsolation("TRANSACTION_REPEATABLE_READ");
		hikariConfig.setPoolName("CustomConnectionPool");
		hikariConfig.setDataSourceProperties(dataSourceProps());
		return hikariConfig;

	}

	protected Properties dataSourceProps() {
		final Properties dataSourceProperties = new Properties();

		dataSourceProperties.put("cachePrepStmts", true);
		dataSourceProperties.put("prepStmtCacheSize", 250);
		dataSourceProperties.put("prepStmtCacheSqlLimit", 2048);
		dataSourceProperties.put("useServerPrepStmts", true);
		dataSourceProperties.put("useLocalSessionState", true);
		dataSourceProperties.put("rewriteBatchedStatements", true);
		dataSourceProperties.put("cacheResultSetMetadata", true);
		dataSourceProperties.put("cacheServerConfiguration", true);
		dataSourceProperties.put("elideSetAutoCommits", true);
		dataSourceProperties.put("maintainTimeStats", false);

		return dataSourceProperties;
	}

}
