package com.demo.poiDemo;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.demo.poiDemo.impl.PoiOperationsImpl;
import com.demo.poiDemo.intf.PoiOperationsIntf;
import com.demo.poiDemo.util.DBConnectionUtil;
import com.demo.poiDemo.util.LoggerConfigUtil;
import com.demo.poiDemo.util.PropertiesLoaderUtil;

/*
 * This is a driver class that demos the poi jar application
 * 
 * @author Soumyadeep
 * 
 * */
public class PoiDemoDriverApp {

	private static HashMap<String, String> dbConfigMap;

	public static void main(String[] args) {
		LoggerConfigUtil.init();
		Logger LOGGER = LogManager.getLogger(PoiDemoDriverApp.class);
		LOGGER.info("Logger Initialized-inisde main method");
		try {
			loadProps();
			LOGGER.info("Setting the configs for DB");
			setDataBaseConfigs();
			// CheckConnectionUtil.checkDbConnection();
			// CheckConnectionUtil.checkBasicDbConnection();
			// CheckConnectionUtil.checkHikariDs();
			// TODO Auto-generated method stub
			PoiOperationsIntf poiOperations = new PoiOperationsImpl();
			EmployeeService employeeService = new EmployeeService();
			employeeService.getEmployeesFromRepo().stream().forEach(employee -> System.out.println(employee));
			// employeeService.createEmployee();
			// List<EmployeeEntity> e = employeeService.getEmployeesList();
			// poiOperations.setData(e);
			// poiOperations.createWorkBook("myworkbook.xls", "Sheet1");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private static void loadProps() {
		dbConfigMap = PropertiesLoaderUtil.getDatabaseProperties();
		// set this into the DBConnectionUtil
	}

	private static void setDataBaseConfigs() {
		DBConnectionUtil dbConnectionUtil = new DBConnectionUtil();
		dbConnectionUtil.setDbConfigsMap(dbConfigMap);
	}

}
