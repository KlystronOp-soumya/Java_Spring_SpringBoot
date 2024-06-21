package com.demo.unittesting.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.unittesting.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
