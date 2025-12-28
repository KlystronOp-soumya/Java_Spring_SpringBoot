package com.demo.azure.servicebus.policyevent;

import com.demo.azure.servicebus.PropsUtil;
import com.demo.azure.servicebus.domain.Customer;
import com.demo.azure.servicebus.domain.Policy;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PolicyBusEventProducer {

    public static void main(String[] args) {
        Policy policy = new Policy(
                "POL-12345",
                "HEALTH",
                new BigDecimal("12000.00"),
                LocalDate.now(),
                LocalDate.now().plusYears(1)
        );

        Customer customer = new Customer(
                "CUST-999",
                "John Doe",
                "john.doe@gmail.com",
                "12345678"
        );

        PolicyCreatedEvent event =
                new PolicyCreatedEvent(policy, customer);

        PolicyEventProducer producer =
                new PolicyEventProducer(
                        PropsUtil.getInstance().getProperty("local.servicebus.connection.string"),
                        "policy.created"
                );

        try {
            producer.sendPolicyCreatedEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
