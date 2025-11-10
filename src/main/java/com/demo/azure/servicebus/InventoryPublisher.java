package com.demo.azure.servicebus;

import com.azure.messaging.servicebus.*;

public class InventoryPublisher {
    private static final String CONNECTION_STRING =
            "Endpoint=sb://localhost:5672/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=your_key";
    private static final String TOPIC_NAME = "inventory-updates";

    public static void main(String[] args) {
        try (ServiceBusSenderClient sender = new ServiceBusClientBuilder()
                .connectionString(CONNECTION_STRING)
                .sender()
                .topicName(TOPIC_NAME)
                .buildClient()) {

            for (int i = 1; i <= 10; i++) {
                String messageBody = String.format("{\"productId\":%d, \"stock\":%d}", i, (100 + i));
                sender.sendMessage(new ServiceBusMessage(messageBody).setSubject("INVENTORY_UPDATE"));
                System.out.println("📤 Sent message: " + messageBody);
            }
        }
    }
}
