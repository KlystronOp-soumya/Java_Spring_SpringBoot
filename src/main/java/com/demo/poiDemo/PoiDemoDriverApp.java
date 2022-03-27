package com.demo.poiDemo;

import java.util.List;

import com.demo.poiDemo.entity.EmployeeEntity;
import com.demo.poiDemo.impl.PoiOperationsImpl;
import com.demo.poiDemo.intf.PoiOperationsIntf;

/*
 * This is a driver class that demos the poi jar application
 * 
 * @author Soumyadeep
 * 
 * */
public class PoiDemoDriverApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PoiOperationsIntf poiOperations = new PoiOperationsImpl();
		EmployeeService employeeService = new EmployeeService();
		employeeService.createEmployee();
		List<EmployeeEntity> e = employeeService.getEmployeesList();
		poiOperations.setData(e);
		poiOperations.createWorkBook("myworkbook.xls", "Sheet1");

	}

}
