package com.demo.azure.servicebus.dlqsimulation;

import com.azure.messaging.servicebus.ServiceBusReceivedMessage;

public class DlqReader {

    public static void main(String[] args) {

        try (var dlqReceiver = ServiceBusClientFactory.dlqReceiver()) {

            for (ServiceBusReceivedMessage msg : dlqReceiver.receiveMessages(10)) {

                System.out.println("DLQ MESSAGE:");
                System.out.println("Body: " + msg.getBody());
                System.out.println("Reason: " + msg.getDeadLetterReason());
                System.out.println("Error: " + msg.getDeadLetterErrorDescription());
                System.out.println("Delivery Count: " + msg.getDeliveryCount());
                System.out.println("-----");

                dlqReceiver.complete(msg);
            }
        }
    }
}
