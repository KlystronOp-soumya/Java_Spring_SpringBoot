package com.demo.jdbcDemo.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.rowset.JdbcRowSet;

import org.apache.log4j.Logger;

import com.demo.jdbcDemo.dao.impl.AgentDaoImpl;
import com.demo.jdbcDemo.dao.intf.AgentDaoIntf;
import com.demo.jdbcDemo.entity.ActorEntity;
import com.demo.jdbcDemo.service.intf.AgentIntf;
import com.demo.jdbcDemo.utils.DBConnectionUtil;

public class AgentImpl implements AgentIntf {

	private static final Logger LOGGER = Logger.getLogger(AgentImpl.class) ;
	
	private transient DataSource dataSource;
	
	private transient AgentDaoIntf agentDao ;
	
	private transient JdbcRowSet rowSet ;
	
	public AgentImpl() {
		// TODO Auto-generated constructor stub
		this.setAgentDao();
		this.agentDao = this.getAgentDao() ;
	}
	
	@Override
	public void checkDBConnection() {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn= dataSource.getConnection();			
			if(null != conn)
				LOGGER.info("Connection estavlished successfully");
			else
				LOGGER.info("Connection not estavlished successfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(conn !=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
		
	}
	
	@Override
	public List<ActorEntity> getActorsDetails() {
		// TODO Auto-generated method stub
		LOGGER.info("In service fetching the list of actors");
		List<ActorEntity> actorList = this.agentDao.getAllActors(rowSet);
		return actorList;
		
	}
	
	
	public void setAgentDao()
	{
		this.agentDao = new AgentDaoImpl() ;
	}
	
	public AgentDaoIntf getAgentDao()
	{
		return this.agentDao ;
	}

	public void setDataSource() {
		this.dataSource = DBConnectionUtil.getDataSource();
	}


	public void setRowSet() {
		this.rowSet = (JdbcRowSet) DBConnectionUtil.getRowSet();
	}

	
	
	
}
