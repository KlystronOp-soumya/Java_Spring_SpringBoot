package com.demo.quarkus.panache.test;

import com.demo.quarkus.jpa.customer.Customer;
import com.demo.quarkus.panache.repos.CustomerRepo;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CustomerRepoTest {

    private Long id;

    @Inject
    private CustomerRepo customerRepo;

    @Test
    @TestTransaction
    public void testSaveCustomerAndFindById() {
        Customer customer = new Customer("John", "Doe", "john.doe@dev.com");

        this.customerRepo.persist(customer);
        Customer customerActual = this.customerRepo.findById(customer.id);
        id = customerActual.id;

       Assertions.assertEquals(customer.id, customerActual.id);
       Assertions.assertEquals(customer.getFirstName(), customerActual.getFirstName());
    }


}
