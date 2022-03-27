package com.demo.poiDemo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.demo.poiDemo.entity.EmployeeEntity;

public class EmployeeService {

	private static final Logger LOGGER = LogManager.getLogger(EmployeeService.class);

	private List<EmployeeEntity> employees;

	public EmployeeService() {
		// TODO Auto-generated constructor stub
		// Instantiate the employee list
		this.employees = new ArrayList<EmployeeEntity>();// initial capacity is 10
		this.createEmployee();
	}

	protected void createEmployee() {
		LOGGER.info("List populated");
		this.employees.add(new EmployeeEntity("Abcd", "Dev", new BigDecimal(100)));
		this.employees.add(new EmployeeEntity("Efgh", "Dev", new BigDecimal(100)));
		this.employees.add(new EmployeeEntity("Ijkl", "Dev", new BigDecimal(100)));
		this.employees.add(new EmployeeEntity("Mnop", "Dev", new BigDecimal(100)));
		this.employees.add(new EmployeeEntity("Qrst", "Dev", new BigDecimal(100)));
		this.employees.add(new EmployeeEntity("Uvwx", "Dev", new BigDecimal(100)));
	}

	public List<EmployeeEntity> getEmployeesList() {
		LOGGER.info("get employee list");
		if (this.employees.isEmpty())
			throw new NullPointerException("List was not created successfully");
		else {
			return this.employees;
		}
	}
}
