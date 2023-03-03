package com.demo.hospitalapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleHospitalApiApplicationTests {
	
	@Autowired
	private DataSource dataSource ;
	
	@PersistenceContext(name = "HospitalApiPersistentUnit" , type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager ;
	
	
	@Test
	public void test_CheckNotNullDataSource()
	{
		
		assertNotNull(dataSource, "The datasource is null");
	}
	
	@Test
	public void test_CheckNotNullEntityManager() {
		assertNotNull(entityManager, "the entitymanager is null");
	}
	
	@Test
	public void test_CheckEntityManagerTransaction() {
		Query query = this.entityManager.createNativeQuery("SELECT COUNT(*) FROM PATIENTS") ;
		
		assertEquals(new BigInteger("4"), query.getSingleResult() , "actual and expected values are not equal");
	}
	

}
