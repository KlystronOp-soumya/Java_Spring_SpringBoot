package com.demo.SpringOrmDemo.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<AgentEntity> getAllActiveAgents() {
		// TODO Auto-generated method stub
		List<AgentEntity> activeAgtList = null;
		Query query = this.sessionFactory.getCurrentSession().createNativeQuery("SELECT * FROM hibjpatest.AGENT",
				AgentEntity.class);
		activeAgtList = new ArrayList<>();
		activeAgtList = (List<AgentEntity>) query.getResultList();
		this.sessionFactory.getCurrentSession().flush();

		if (activeAgtList.isEmpty() || activeAgtList == null)
			throw new NullPointerException("Agent List can not be empty");

		return activeAgtList;
	}

}
