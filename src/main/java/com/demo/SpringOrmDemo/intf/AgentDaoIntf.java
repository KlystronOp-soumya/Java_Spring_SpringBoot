package com.demo.SpringOrmDemo.intf;

import java.util.List;

import org.hibernate.SessionFactory;

import com.demo.SpringOrmDemo.entity.AgentEntity;

public interface AgentDaoIntf {

	void setSession(SessionFactory sessionFactory);

	void saveAgent();

	List<AgentEntity> getAllActiveAgents();
}
