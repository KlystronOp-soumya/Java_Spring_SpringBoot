package com.demo.batch.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Configuration
@PropertySource(value = { "classpath:db-config.properties" })
public class DataSourceConfig {

	private transient DataSourceBuilder<?> dataSourceBuilder;

	@Value(value = "${mysql.url}")
	@NonNull
	private String dbUrlMySql;
	@NonNull
	@Value(value = "${mysql.driver}")
	private String dbDriverMySql;
	@NonNull
	@Value(value = "${mysql.user}")
	private String userMySql;
	@NonNull
	@Value(value = "${mysql.password}")
	private String pwdMySql;

	@NonNull
	@Value(value = "${h2.url}")
	private String dbUrlH2;
	@NonNull
	@Value(value = "${h2.driver}")
	private String dbDriverH2;
	@NonNull
	@Value(value = "${h2.user}")
	private String userH2;
	@Nullable
	@Value(value = "${h2.password}")
	private String pwdH2;

	@Bean(name = "mysqlDS")
	@Primary
	public DataSource dataSourceMySql() {
		this.dataSourceBuilder = DataSourceBuilder.create();
		this.dataSourceBuilder.driverClassName(this.dbDriverMySql);
		this.dataSourceBuilder.url(this.dbUrlMySql);
		this.dataSourceBuilder.username(this.userMySql);
		this.dataSourceBuilder.password(this.pwdMySql);
		return dataSourceBuilder.build();
	}

	@Bean(name = "dh2DS")
	public DataSource dataSourceH2() {
		this.dataSourceBuilder = DataSourceBuilder.create();
		this.dataSourceBuilder.driverClassName(this.dbDriverH2);
		this.dataSourceBuilder.url(this.dbUrlH2);
		this.dataSourceBuilder.username(this.userH2);
		this.dataSourceBuilder.password(this.pwdH2);
		return dataSourceBuilder.build();
	}

	@Bean
	public DataSourceInitializer h2DatabasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("org/springframework/batch/core/schema-h2.sql"));
		populator.setContinueOnError(true);
		populator.setIgnoreFailedDrops(true);

		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDatabasePopulator(populator);
		initializer.setDataSource(dataSourceH2());

		return initializer;
	}
}
