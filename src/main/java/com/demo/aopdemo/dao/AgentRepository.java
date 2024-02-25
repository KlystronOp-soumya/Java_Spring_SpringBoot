package com.demo.aopdemo.dao;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.aopdemo.entity.Agent;


public interface AgentRepository extends JpaRepository<Agent, Long> , CustomAgentDaoIntf {
	@Modifying
	@Query(name = "insertAgent" , nativeQuery = true , value = "INSERT INTO AGENT VALUES(:agtId , :agtNum , "
			+ " :agtFname , :agtLname , :paidComm)")
	public void insertAgent( @Param("agtId") Long agtId , @Param("agtNum") String agtNum , 
			@Param("agtFname") String agtFname , @Param("agtLname") String agtLname , @Param("paidComm") BigDecimal paidComm) ;
	
	public Optional<Agent> findByAgtId(Long agtId);
	public Optional<Agent> findByAgtIdAndAgtNum(Long agtId , String agtNum) ;
}
