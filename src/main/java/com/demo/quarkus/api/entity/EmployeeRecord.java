package com.demo.quarkus.api.entity;

import java.io.Serializable;

public record EmployeeRecord(String id , String firstName , String lastName, String role , String empDept) implements Serializable{

}
