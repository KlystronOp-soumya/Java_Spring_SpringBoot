package com.demo.jdbcDemo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.JdbcRowSet;

import org.apache.log4j.Logger;

import com.demo.jdbcDemo.dao.intf.AgentDaoIntf;
import com.demo.jdbcDemo.entity.ActorEntity;

public class AgentDaoImpl implements AgentDaoIntf {

	private static final Logger LOGGER = Logger.getLogger(AgentDaoImpl.class) ;
	
	@Override
	public List<ActorEntity> getAllActors(JdbcRowSet rowSet) {
		// TODO Auto-generated method stub
		LOGGER.info("Inside DAO fetching the list of actors");
		List<ActorEntity> actEntList = new ArrayList<>();
		String query = "SELECT * FROM ACTOR LIMIT 5" ;
		try {
			rowSet.setCommand(query);
			rowSet.execute();
			
			while(rowSet.next())
			{
				ActorEntity actEnt = new ActorEntity();
				actEnt.setId(rowSet.getInt("actor_id"));
				actEnt.setFname(rowSet.getString("first_name"));
				actEnt.setLname(rowSet.getString("last_name"));
				
				actEntList.add(actEnt);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return actEntList;
	}

}
