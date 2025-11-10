package com.demo.azure.servicebus;

import com.azure.messaging.servicebus.*;
import java.util.List;
import java.util.concurrent.*;

public class InventorySubscriber {
    private static final String CONNECTION_STRING =
            "Endpoint=sb://localhost:5672/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=your_key";
    private static final String TOPIC_NAME = "inventory-updates";

    public static void main(String[] args) throws InterruptedException {
        List<String> subscriptions = List.of("stock-manager", "pricing-engine", "analytics");

        // Create a fixed pool matching subscriber count (or larger)
        ExecutorService executor = Executors.newFixedThreadPool(subscriptions.size());

        for (String sub : subscriptions) {
            executor.submit(() -> startProcessor(sub));
        }

        // Keep the program running
        System.out.println("✅ Subscribers are running. Press Ctrl+C to exit.");
        Thread.currentThread().join();  // Prevent main from exiting
    }

    private static void startProcessor(String subscriptionName) {
        ServiceBusProcessorClient processor = new ServiceBusClientBuilder()
                .connectionString(CONNECTION_STRING)
                .processor()
                .topicName(TOPIC_NAME)
                .subscriptionName(subscriptionName)
                .processMessage(context -> {
                    String body = context.getMessage().getBody().toString();
                    System.out.printf("[%s] ✅ Received message: %s%n", subscriptionName, body);
                })
                .processError(context -> {
                    System.err.printf("[%s] ❌ Error: %s%n",
                            subscriptionName, context.getException().getMessage());
                })
                .maxConcurrentCalls(3) // concurrent messages per subscriber
                .buildProcessorClient();

        System.out.printf("[%s] 🔄 Starting processor...%n", subscriptionName);
        processor.start();
    }
}

