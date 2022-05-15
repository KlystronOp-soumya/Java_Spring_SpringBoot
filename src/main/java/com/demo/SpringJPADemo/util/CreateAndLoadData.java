package com.demo.SpringJPADemo.util;

import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.demo.SpringJPADemo.intf.ClearAndLoadIntf;

public class CreateAndLoadData implements ClearAndLoadIntf {
	/*
	 * This class uses the resource populator to create and insert the data using
	 * Scripts
	 * 
	 * 
	 */

	private ResourceDatabasePopulator resourceDatabasePopulator;

	public void setResourceDatabasePopulator(ResourceDatabasePopulator resourceDatabasePopulator) {
		this.resourceDatabasePopulator = resourceDatabasePopulator;
	}

	@Override
	public void createDBTables() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertDBData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllDBData() {
		// TODO Auto-generated method stub

	}

}
