package com.restdemo.RestDemo;
/*
 * In case of 204 error handle it with 404
 * also prompt the client what to do in that case
 * */

import com.pojo.Employees;
import com.repositories.EmployeesRepository;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employees")
public class EmployeesResource {
	
	EmployeesRepository emp_Repository=new EmployeesRepository();//list population

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Employees> getEmployees(){
		 //System.out.println(" Called");
		return emp_Repository.getEmployees();
	}
	
	 @Path("/search/{emp_id}")
	  @GET		
		 @Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
		 
	  @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	 public Employees searchEmployees(@PathParam("emp_id")int emp_id) {
		 //System.out.println("Search Called");
		  return emp_Repository.getEmployeeById(emp_id);
	  }
	 
	 @Path("/register")
	 @POST
	 @Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	  @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	 public List< Employees > createEmployee(Employees new_emp) {
		 System.out.println(new_emp);
		 
		List< Employees >created_Employee=emp_Repository.createEmployee(new_emp);
		 return created_Employee;
	 }
	 
	 @Path("/update/{emp_id}")
	 @PUT
	 @Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	  @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	 //public List<Employees> updateEmployee(Employees emp){
	 
	 public Employees updateEmployee(Employees emp) {
		 Employees ref_Employees=emp_Repository.updateEmployee(emp);
		 return ref_Employees;
		// return emp_Repository.getEmployees(); //to check if update is done
	 }
	 
	 @Path("/delete/{emp_id}")
	 @DELETE
	 @Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	 public List<Employees> deleteEmployees(@PathParam("emp_id")int emp_id){
		 
		 return emp_Repository.deleteEmployee(emp_id);
	 }
}
