package com.demo.SpringOrmDemo.DAO;

import java.util.List;

import org.hibernate.SessionFactory;

import com.demo.SpringOrmDemo.entity.AgentEntity;
import com.demo.SpringOrmDemo.intf.AgentDaoIntf;

public class AgentDAO implements AgentDaoIntf {

	@Override
	public void setSession(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAgent() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AgentEntity> getAllActiveAgents() {
		// TODO Auto-generated method stub
		return null;
	}

}
