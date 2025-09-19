package com.demo.quarkus.api.mappers;

import org.mapstruct.Mapper;
import com.demo.quarkus.api.entity.EmployeeEntity;
import com.demo.quarkus.api.entity.EmployeeRecord;

@Mapper(componentModel = "cdi")
public interface EmpRecToEntity {

    //@Mapping(source = "firstName", target = "firstName")
    //@Mapping(source = "lastName", target = "lastName")
    EmployeeEntity toEmployeeEntity(EmployeeRecord employeeRecord);

    //@Mapping(source = "firstName", target = "firstName")
    //@Mapping(source = "lastName", target = "lastName")
    EmployeeRecord toEmployeeRecord( EmployeeEntity employeeEntity);

}
