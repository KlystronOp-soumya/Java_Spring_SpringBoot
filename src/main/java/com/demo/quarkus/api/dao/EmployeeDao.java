package com.demo.quarkus.api.dao;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.demo.quarkus.api.entity.EmployeeEntity;
import com.demo.quarkus.api.entity.EmployeeRecord;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmployeeDao {

    @Inject
    private Logger logger;

    @ConfigProperty(name = "employee.department", defaultValue = "None")
    private String empDept;

    private List<EmployeeRecord> employees;

    public List<EmployeeRecord> findAllEmployees() {
        logger.info("Trying to get all employees from Database");

        logger.info("returning result to service");
        return employees;
    }

    public EmployeeRecord findEmployeeById(final String empId) {

        final EmployeeRecord foundEmp = employees.stream().filter((eachEmp) -> eachEmp.id().equals(empId)).findFirst()
                .orElse(null);
        logger.info("Found employee in DB : " + ((null != foundEmp) ? "Y" : "N"));
        return foundEmp;
    }

    public EmployeeEntity updateEmployee(final EmployeeEntity employeeEntity) {
        logger.info("Trying to update employee record in DB : " + employeeEntity.getId());
        EmployeeRecord empRecord = employees.stream()
                .filter((eachEmpRec) -> eachEmpRec.id().equals(employeeEntity.getId())).findFirst().orElse(null);

        if (empRecord != null) {
            logger.info("Updating employee record...");
            // delete the record from the list
            employees.remove(empRecord);
            // then create a new record and put it into the list
            EmployeeRecord rec = new EmployeeRecord(employeeEntity.getId(), employeeEntity.getFirstName(),
                    employeeEntity.getLastName(), employeeEntity.getRole(), employeeEntity.getEmpDept());
            employees.add(rec);
            logger.info("Record was updated successfully");
        } else {
            logger.info("Could not update record");
        }

        return employeeEntity;

    }

    @PostConstruct
    private void init() {

        employees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            EmployeeRecord employeeRecord = new EmployeeRecord(String.valueOf(i), "John", "Doe", "None", empDept);
            employees.add(employeeRecord);

        }
    }

}
