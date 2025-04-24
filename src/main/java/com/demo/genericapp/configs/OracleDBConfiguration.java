package com.demo.genericapp.configs;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.genericapp.configs.configprops.OracleDbConfigProps;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(value = { OracleDbConfigProps.class })
public class OracleDBConfiguration {

	@Bean
	OracleDbConfigProps oracleDbConfigProps() {
		return new OracleDbConfigProps();
	}

}
