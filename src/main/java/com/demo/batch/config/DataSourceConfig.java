package com.demo.batch.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
	private String dbUrlH2;
	@NonNull
	private String dbDriverH2;
	@NonNull
	private String userH2;
	@Nullable
	private String pwdH2;

	public DataSource dataSourceMySql() {
		this.dataSourceBuilder = DataSourceBuilder.create();
		this.dataSourceBuilder.driverClassName(this.dbDriverMySql);
		this.dataSourceBuilder.url(this.dbUrlMySql);
		this.dataSourceBuilder.username(this.userMySql);
		this.dataSourceBuilder.password(this.pwdMySql);
		return dataSourceBuilder.build();
	}

	public DataSource dataSourceH2() {
		this.dataSourceBuilder = DataSourceBuilder.create();
		this.dataSourceBuilder.driverClassName(this.dbDriverH2);
		this.dataSourceBuilder.url(this.dbUrlH2);
		this.dataSourceBuilder.username(this.userH2);
		this.dataSourceBuilder.password(this.pwdH2);
		return dataSourceBuilder.build();
	}
}
