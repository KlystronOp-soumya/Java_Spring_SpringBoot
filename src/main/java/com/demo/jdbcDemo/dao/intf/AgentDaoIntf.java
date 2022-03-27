package com.demo.jdbcDemo.dao.intf;

import java.util.List;

import javax.sql.rowset.JdbcRowSet;

import com.demo.jdbcDemo.entity.ActorEntity;

public interface AgentDaoIntf {
	List<ActorEntity> getAllActors(JdbcRowSet rowSet);
	
}
