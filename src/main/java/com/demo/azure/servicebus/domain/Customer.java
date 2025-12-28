package com.demo.azure.servicebus.domain;

public class Customer {

    private String customerId;
    private String name;
    private String email;
    private String phone;

    public Customer() {}

    public Customer(String customerId, String name,
                    String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
