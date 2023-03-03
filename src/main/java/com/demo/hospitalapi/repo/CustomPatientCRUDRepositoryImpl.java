package com.demo.hospitalapi.repo;

import org.springframework.jdbc.core.JdbcTemplate;

import com.demo.hospitalapi.repo.intf.CustomPatientCRUDRepository;

public class CustomPatientCRUDRepositoryImpl implements CustomPatientCRUDRepository {
	
	private JdbcTemplate jdbcTemplate ;
	
	public CustomPatientCRUDRepositoryImpl(final JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated constructor stub
		this.jdbcTemplate = jdbcTemplate ;
	}
}
