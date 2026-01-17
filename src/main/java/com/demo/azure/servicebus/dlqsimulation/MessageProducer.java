package com.demo.azure.servicebus.dlqsimulation;

import com.azure.messaging.servicebus.ServiceBusMessage;

public class MessageProducer {

    public static void main(String[] args) {
        try (var sender = ServiceBusClientFactory.sender()) {
            sender.sendMessage(new ServiceBusMessage("ORDER-123"));
            System.out.println("Message sent successfully");
        }
    }
}
