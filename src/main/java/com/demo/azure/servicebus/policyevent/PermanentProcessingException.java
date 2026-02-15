package com.demo.azure.servicebus.policyevent;

public class PermanentProcessingException extends RuntimeException {
    public PermanentProcessingException(String msg) {
        super(msg);
    }
}
