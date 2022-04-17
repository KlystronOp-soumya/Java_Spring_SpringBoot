package com.demo.SpringOrmDemo.DAO;

import java.util.List;

import org.hibernate.SessionFactory;

import com.demo.SpringOrmDemo.entity.AgentEntity;
import com.demo.SpringOrmDemo.intf.AgentDao;

public class AgentDaoImpl implements AgentDao {

	private transient SessionFactory sessionFactory;

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveAgent(AgentEntity agtEntity) {
		// TODO Auto-generated method stub
		// this.sessionFactory.getCurrentSession().save(agtEntity);
		this.sessionFactory.getCurrentSession().persist(agtEntity);
		this.sessionFactory.getCurrentSession().flush();
		// this.sessionFactory.getCurrentSession().close();
	}

	@Override
	public List<AgentEntity> getAllActiveAgents() {
		// TODO Auto-generated method stub
		return null;
	}

}
