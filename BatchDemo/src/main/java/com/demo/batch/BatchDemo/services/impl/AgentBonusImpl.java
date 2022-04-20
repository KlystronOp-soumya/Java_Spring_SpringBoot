package com.demo.batch.BatchDemo.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.batch.BatchDemo.DAO.intf.AgentBonusDAO;
import com.demo.batch.BatchDemo.entity.Agent;
import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;
import com.demo.batch.BatchDemo.services.intf.AgentBonusIntf;
import com.demo.batch.Util.InstanceUtil;

public class AgentBonusImpl implements AgentBonusIntf {

	/**/
	private List<Agent> agentList;
	private List<AgentBnsQualifEntity> agtBnsQualifList;

	@Autowired
	private AgentBonusDAO agtBnsDao;

	@Override
	public void createAgents() {
		// TODO Auto-generated method stub
		Agent obj1 = InstanceUtil.getAgentInstance();
		obj1.setName("ABCD");
		obj1.setDesignation("DEV");
		obj1.setLOB("10");
		obj1.setBonusAmt(new BigDecimal(1500));

		Agent obj2 = InstanceUtil.getAgentInstance();
		obj2.setName("EFGH");
		obj2.setDesignation("DEV Jr.");
		obj2.setLOB("10");
		obj2.setBonusAmt(new BigDecimal(1000));

		Agent obj3 = InstanceUtil.getAgentInstance();
		obj3.setName("IJKL");
		obj3.setDesignation("Manager");
		obj3.setLOB("10");
		obj3.setBonusAmt(new BigDecimal(2000));

		agentList = new ArrayList();
		agentList.add(obj1);
		agentList.add(obj2);
		agentList.add(obj3);

	}

	@Override
	public List<Agent> getAgents() {
		// TODO Auto-generated method stub
		return this.agentList;
	}

	@Override
	public List<AgentBnsQualifEntity> getAgtQualifList() {
		// TODO Auto-generated method stub
		return this.agtBnsDao.getAgentQualifData();
	}

	@Override
	public void calculateAgentBns(Agent agtObj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayAgentDetails() {
		// TODO Auto-generated method stub
		System.out.println("Details are as follows: ");
		for (Agent eachObjAgent : this.agentList) {
			System.out.println(eachObjAgent);
		}

	}

	@Override
	public void displayAgtBnsDetails() {
		// TODO Auto-generated method stub

	}

}
