package com.demo.springbootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.springbootsecurity.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
