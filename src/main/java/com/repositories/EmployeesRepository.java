 package com.repositories;

import java.util.*;
import com.pojo.Employees;


public class EmployeesRepository {
	
	//Employees employees;
	 private List<Employees>employees_List=new ArrayList<Employees>();
	 
	 public EmployeesRepository() {
		 //initialize and store into the list
		    Employees employees1=new Employees();
			Employees employees2=new Employees();
			Employees employees3=new Employees();
			Employees employees4=new Employees();
			Employees employees5=new Employees();
			Employees employees6=new Employees();
			
			//inserted data
			employees1.setEmp_id(1);
			employees1.setFname("Paul");
			employees1.setLname("Soumyadeep");
			employees1.setPosition("Developer");
			employees1.setSalary(500000);
			
			employees2.setEmp_id(2);
			employees2.setFname("Mondal");
			employees2.setLname("Swastik");
			employees2.setPosition("Developer");
			employees2.setSalary(500000);
			
			employees3.setEmp_id(3);
			employees3.setFname("Roy");
			employees3.setLname("Aniket");
			employees3.setPosition("DBA");
			employees3.setSalary(500000);
			
			employees4.setEmp_id(4);
			employees4.setFname("Chatterjee");
			employees4.setLname("Soumyabrata");
			employees4.setPosition("DBA");
			employees4.setSalary(500000);
			
			employees5.setEmp_id(5);
			employees5.setFname("Mukherjee");
			employees5.setLname("Oishik");
			employees5.setPosition("Tester");
			employees5.setSalary(500000);
			
			employees6.setEmp_id(6);
			employees6.setFname("Saha");
			employees6.setLname("Dishani");
			employees6.setPosition("Manager");
			employees6.setSalary(500000);
			
			//inserted into the list
			employees_List.add(employees1);
			employees_List.add(employees2);
			employees_List.add(employees3);
			employees_List.add(employees4);
			employees_List.add(employees5);
			employees_List.add(employees6);	 
	 }
	 
	 //post
	 public List<Employees> createEmployee(Employees new_emp) {
		 //Employees temp_obj = new Employees();
		 try {
			 
				/*
				 * temp_obj.setEmp_id(emp_id); temp_obj.setFname(fname);
				 * temp_obj.setLname(lname); temp_obj.setPosition(designation);
				 * temp_obj.setSalary(salary);
				 */
			 
			 employees_List.add(new_emp);
			 
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		 return employees_List;	//new appended list
	}
	 
	 
	 //get
	public List<Employees> getEmployees () {
		if(employees_List.isEmpty())
			return null;
		else {
			return employees_List;
		}
	 
	}
	
	//get
	public Employees getEmployeeById(int emp_id) {
		System.out.println("getEmployeeById called");
		for(Employees e:employees_List) {
			if(e.getEmp_id() == emp_id)
				return e;
			}
		return null;
	}
	
	//put
	public Employees updateEmployee(Employees emp) {
		for(Employees e:employees_List) {
			if(e.getEmp_id() == emp.getEmp_id())
			{
				e.setFname(emp.getFname());
				e.setLname(emp.getLname());
				e.setPosition(emp.getPosition());
				e.setSalary(emp.getSalary());
				return e;
			}
			}
		return null;
		
	}
	
	//delete
	public List<Employees> deleteEmployee(int emp_id) {
		for(Employees each_emp:employees_List) {
			if(each_emp.getEmp_id() == emp_id) {
				//delete the the object
				try {
					employees_List.remove(each_emp);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return employees_List;
			}
		}
		return null;
	}

}
