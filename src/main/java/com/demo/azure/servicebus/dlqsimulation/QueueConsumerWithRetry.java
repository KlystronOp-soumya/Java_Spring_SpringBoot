package com.demo.azure.servicebus.dlqsimulation;

import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.models.DeadLetterOptions;

public class QueueConsumerWithRetry {
    private static final int MAX_RETRIES = 3;

    public static void main(String[] args) {

        try (var receiver = ServiceBusClientFactory.receiver()) {

            ServiceBusReceivedMessage message = receiver.receiveMessages(1)
                    .stream().findFirst().orElse(null);

            if (message == null) {
                System.out.println("No messages available");
                return;
            }

            int deliveryCount = Math.toIntExact(message.getDeliveryCount());

            try {
                System.out.println("Processing message: " + message.getBody().toString());

                // Simulate failure
                throw new RuntimeException("Simulated processing failure");

            } catch (Exception ex) {

                if (deliveryCount >= MAX_RETRIES) {
                    System.out.println("Max retries reached. Dead-lettering message.");

                    DeadLetterOptions deadLetterOptions = new DeadLetterOptions();
                    deadLetterOptions.setDeadLetterReason("Forced failed");

                    receiver.deadLetter(
                            message, deadLetterOptions
                    );
                } else {
                    System.out.println("Abandoning message for retry");
                    receiver.abandon(message);
                }
            }
        }
    }
}
