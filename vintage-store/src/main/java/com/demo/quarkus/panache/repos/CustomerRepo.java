package com.demo.quarkus.panache.repos;

import com.demo.quarkus.jpa.customer.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.inject.Singleton;

@Singleton
public class CustomerRepo implements PanacheRepository<Customer> {
}
