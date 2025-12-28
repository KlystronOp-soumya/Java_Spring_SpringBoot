package com.demo.azure.servicebus.policyevent;

public enum EventType {
    POLICY_CREATED("policy.created"),
    POLICY_UPDATED("policy.updated"),
    POLICY_CANCELLED("policy.cancelled");

    private String value;

    EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
