package com.demo.poiDemo;

import com.demo.poiDemo.util.PropertiesLoaderUtil;

/*
 * This is a driver class that demos the poi jar application
 * 
 * @author Soumyadeep
 * 
 * */
public class PoiDemoDriverApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// PoiOperationsIntf poiOperations = new PoiOperationsImpl();
		// EmployeeService employeeService = new EmployeeService();
		// employeeService.createEmployee();
		// List<EmployeeEntity> e = employeeService.getEmployeesList();
		// poiOperations.setData(e);
		// poiOperations.createWorkBook("myworkbook.xls", "Sheet1");
		loadProps();
	}

	private static void loadProps() {
		PropertiesLoaderUtil obj = new PropertiesLoaderUtil();
		obj.getDatabaseProperties();
	}

}
