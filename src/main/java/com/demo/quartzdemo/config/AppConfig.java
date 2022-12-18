package com.demo.quartzdemo.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = { "com.demo.quartzdemo" })
@Import(value = { CustomQuartzConfig.class, BatchJobConfig.class, BatchJobExecConfig.class })
public class AppConfig {

}
