package com.demo.aopdemo.dao;

import org.springframework.data.repository.CrudRepository;

import com.demo.aopdemo.entity.Agent;

public interface AgentCrudRepo extends CrudRepository<Agent	, Long>{

}
