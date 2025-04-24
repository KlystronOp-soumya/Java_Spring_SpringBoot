package com.demo.genericapp.configs.configprops;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "db", ignoreInvalidFields = false, ignoreUnknownFields = false)
//@PropertySource(value = "classpath:/dbconfig.db_config.properties")
public class OracleDbConfigProps {

	private String driverclass;
	private String url;
	private String user;
	private String password;
	private String schema;

	public String getDriverclass() {
		return driverclass;
	}

	public void setDriverclass(String driverclass) {
		this.driverclass = driverclass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

}
