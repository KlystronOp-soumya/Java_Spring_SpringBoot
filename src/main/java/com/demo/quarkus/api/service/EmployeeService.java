package com.demo.quarkus.api.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;

import com.demo.quarkus.api.dao.EmployeeDao;
import com.demo.quarkus.api.entity.EmployeeEntity;
import com.demo.quarkus.api.entity.EmployeeRecord;
import com.demo.quarkus.api.mappers.EmpRecToEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmployeeService {

    @Inject
    private Logger logger ;

    @Inject
    private transient EmployeeDao empDao ;

    @Inject
    private transient EmpRecToEntity mapper ;

    public Optional<List<EmployeeRecord>> getEmployees(){
        logger.info("Trying to get all employees");
        return Optional.of(empDao.findAllEmployees()) ;
        
    }

    public EmployeeRecord getEmployeeById(final String empId){
        logger.info("Trying to find employee : " + empId);
        return empDao.findEmployeeById(empId) ;
    }

    public EmployeeRecord updateEmployee( EmployeeRecord employeeRecord){
    logger.info("mapping to_update employee record to entity");
       EmployeeEntity updEmployeeEntity = empDao.updateEmployee(mapper.toEmployeeEntity(employeeRecord) ) ;
        logger.info("mapping updated entity with record...");
        return mapper.toEmployeeRecord(updEmployeeEntity) ;
    }
}
