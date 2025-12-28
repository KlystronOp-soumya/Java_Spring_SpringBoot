package com.demo.azure.servicebus.policyevent;

import com.demo.azure.servicebus.domain.Customer;
import com.demo.azure.servicebus.domain.Policy;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class PolicyCreatedEvent implements Serializable {

    private String eventId;
    private EventType eventType;
    private Instant occurredOn;

    private Policy policy;
    private Customer customer;

    // Required by Jackson
    public PolicyCreatedEvent() {}

    public PolicyCreatedEvent(Policy policy, Customer customer) {
        this.eventId = UUID.randomUUID().toString();
        this.eventType = EventType.POLICY_CREATED;
        this.occurredOn = Instant.now();
        this.policy = policy;
        this.customer = customer;
    }

    public String getEventId() { return eventId; }
    public EventType getEventType() { return eventType; }
    public Instant getOccurredOn() { return occurredOn; }
    public Policy getPolicy() { return policy; }
    public Customer getCustomer() { return customer; }
}
