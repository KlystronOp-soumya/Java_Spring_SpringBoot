package com.fresco.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component(value = "hospitalRepository")
public interface HospitalRepo extends CrudRepository<Hospital, Integer> {

}
