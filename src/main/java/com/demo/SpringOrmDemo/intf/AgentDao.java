package com.demo.SpringOrmDemo.intf;

import java.util.List;

import org.hibernate.SessionFactory;

import com.demo.SpringOrmDemo.entity.AgentEntity;

public interface AgentDao {

	void setSessionFactory(SessionFactory sessionFactory);

	void saveAgent(AgentEntity agtEntity);

	List<AgentEntity> getAllActiveAgents();
}
