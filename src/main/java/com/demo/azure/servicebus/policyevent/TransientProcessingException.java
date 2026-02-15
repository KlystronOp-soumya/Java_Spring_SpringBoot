package com.demo.azure.servicebus.policyevent;

public class TransientProcessingException extends RuntimeException {
    public TransientProcessingException(String msg) {
        super(msg);
    }
}
