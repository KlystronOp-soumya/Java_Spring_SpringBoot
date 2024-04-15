package com.demo.springbootsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.springbootsecurity.entities.Roles;



public interface CustomRoleRepository extends JpaRepository<Roles, Integer> {

}
