package com.demo.hospitalapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import com.demo.hospitalapi.config.dev.DatabasePersistentConfig;
import com.demo.hospitalapi.config.dev.SwaggerConfig;
import com.demo.hospitalapi.controller.PatientController;
import com.demo.hospitalapi.repo.CustomPatientCRUDRepositoryImpl;
import com.demo.hospitalapi.repo.PatientRepository;
import com.demo.hospitalapi.repo.intf.CustomPatientCRUDRepository;
import com.demo.hospitalapi.service.PatientService;

@Configuration
//@Profile(value = "dev")
@Import(value = {DatabasePersistentConfig.class , com.demo.hospitalapi.config.qa.DatabasePersistentConfig.class , SwaggerConfig.class, AppCacheConfig.class})
@ComponentScan(basePackages = "com.demo.hospitalapi")
@EnableJpaRepositories(basePackages = "com.demo.hospitalapi.repo")
public class AppConfig {

		
	  private PatientRepository patientRepository ;
	  private JdbcTemplate jdbcTemplate ;
	  public AppConfig(PatientRepository patientRepository , JdbcTemplate jdbcTemplate) { // TODO
	 
	  this.patientRepository = patientRepository ;
	  this.jdbcTemplate = jdbcTemplate ;
	  }
	 
	
	@Bean
	public PatientService patientService() {
		return new PatientService(this.patientRepository) ;
	}
	
	@Bean
	public PatientController patientController()
	{
		return new PatientController(patientService()) ;
	}
	
	@Bean
	public CustomPatientCRUDRepository customPatientCRUDRepository()
	{
		return new CustomPatientCRUDRepositoryImpl(this.jdbcTemplate) ;
	}
	
	
}
