package com.demo.jdbcDemo.service.intf;

import java.util.List;

import com.demo.jdbcDemo.entity.ActorEntity;

public interface AgentIntf {

	void checkDBConnection() ;
	void setDataSource();
	void setRowSet();
	List<ActorEntity> getActorsDetails(); 
}
