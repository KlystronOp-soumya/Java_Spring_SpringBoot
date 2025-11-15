package com.demo.quarkus.jpa.customer.test;

import com.demo.quarkus.jpa.customer.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CustomerRepo {

    @PersistenceContext
    private EntityManager em;

    public void persist(Customer customer) {
        this.em.persist(customer);
    }

    public Customer findById(Long id) {
        return this.em.find(Customer.class, id);
    }
}
