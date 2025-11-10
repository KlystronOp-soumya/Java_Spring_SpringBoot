package com.demo.azure.servicebus;

import com.azure.messaging.servicebus.*;

public class DLQReader {
    private static final String CONNECTION_STRING =
            "Endpoint=sb://localhost:5672/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=your_key";
    private static final String TOPIC_NAME = "inventory-updates";
    private static final String SUBSCRIPTION_NAME = "stock-manager";

    public static void main(String[] args) {
        // Build the DLQ path manually
        String dlqPath = String.format("%s/Subscriptions/%s/$DeadLetterQueue",
                TOPIC_NAME, SUBSCRIPTION_NAME);

        ServiceBusReceiverClient dlqReceiver = new ServiceBusClientBuilder()
                .connectionString(CONNECTION_STRING)
                .receiver()
                .queueName(dlqPath)
                .buildClient();

        System.out.printf("🔍 Reading messages from DLQ: %s%n", dlqPath);

        dlqReceiver.receiveMessages(10).forEach(msg -> {
            System.out.println("☠️ DLQ message: " + msg.getBody().toString());
        });

        dlqReceiver.close();
    }
}

