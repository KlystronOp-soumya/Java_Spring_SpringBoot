package com.demo.batch.BatchDemo.services.impl;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.demo.batch.BatchDemo.DAO.intf.InitializeAndClearDAOIntf;
import com.demo.batch.BatchDemo.services.intf.InitAndClearIntf;

public class InitAndClearService implements InitAndClearIntf {

	/* The Logger */
	private static final Logger LOGGER = Logger.getLogger(InitAndClearService.class);
	// DAO
	private transient InitializeAndClearDAOIntf initAndClrDao;

	public void setInitAndClrDao(InitializeAndClearDAOIntf initAndClrDao) {
		this.initAndClrDao = initAndClrDao;
	}

	@Override
	@Transactional
	public void clearAgentBnsQualif() {
		// TODO Auto-generated method stub
		LOGGER.info("Inside clear Agent Bonus Qualification Table");
		try {
			initAndClrDao.deleteTable();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void insertAgentDBData() {
		// TODO Auto-generated method stub

	}

}
