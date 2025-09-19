package com.demo.quarkus.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeEntity {

    private String id ;
    private String firstName ;
    private String lastName ;
    private String role ;
    private String empDept ;

}
