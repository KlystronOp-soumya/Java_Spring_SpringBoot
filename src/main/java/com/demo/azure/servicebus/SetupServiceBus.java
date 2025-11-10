package com.demo.azure.servicebus;

import com.azure.messaging.servicebus.administration.*;
import com.azure.messaging.servicebus.administration.models.*;

public class SetupServiceBus {
    private static final String CONNECTION_STRING =
            "Endpoint=sb://localhost:5672/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=your_key";
    private static final String TOPIC_NAME = "inventory-updates";

    public static void main(String[] args) {
        ServiceBusAdministrationClient adminClient = new ServiceBusAdministrationClientBuilder()
                .connectionString(CONNECTION_STRING)
                .buildClient();

        if (!adminClient.getTopicExists(TOPIC_NAME)) {
            adminClient.createTopic(TOPIC_NAME);
            System.out.println("✅ Created topic: " + TOPIC_NAME);
        }

        createSubscriptionIfNotExists(adminClient, "stock-manager");
        createSubscriptionIfNotExists(adminClient, "pricing-engine");
        createSubscriptionIfNotExists(adminClient, "analytics");

        System.out.println("✅ Setup complete.");
    }

    private static void createSubscriptionIfNotExists(ServiceBusAdministrationClient adminClient, String subscriptionName) {
        if (!adminClient.getSubscriptionExists(TOPIC_NAME, subscriptionName)) {
            adminClient.createSubscription(TOPIC_NAME, subscriptionName);
            System.out.println("✅ Created subscription: " + subscriptionName);
        }
    }
}

