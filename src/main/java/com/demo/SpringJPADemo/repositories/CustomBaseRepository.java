package com.demo.SpringJPADemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.demo.SpringJPADemo.models.AgentEntPK;
import com.demo.SpringJPADemo.models.AgentEntity;

@NoRepositoryBean
public interface CustomBaseRepository<T, S> extends JpaRepository<AgentEntity, AgentEntPK> {

}
