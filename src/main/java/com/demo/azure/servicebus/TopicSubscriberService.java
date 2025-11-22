package com.demo.azure.servicebus;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class TopicSubscriberService {

    private final String connectionString;

    private final String topicName;

    private final String subscriptionName;

    private ServiceBusProcessorClient serviceBusProcessorClient;

    private ExecutorService executorService;

    private final AtomicBoolean running = new AtomicBoolean(false);

    public TopicSubscriberService(String connectionString, String topicName, String subscriptionName) {
        this.connectionString = connectionString;
        this.topicName = topicName;
        this.subscriptionName = subscriptionName;
    }

    public void start() {

        if (running.get()) {
            System.out.println("Already running");
            return;
        }
        running.set(true);
        executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {

            while (running.get()) {

                try {
                    serviceBusProcessorClient = new ServiceBusClientBuilder()
                            .connectionString(connectionString)
                            .processor()
                            .topicName(topicName)
                            .subscriptionName(subscriptionName)
                            .processMessage(TopicSubscriberService::processMessage)
                            .processError(TopicSubscriberService::processErrorMessage)
                            .buildProcessorClient();

                    serviceBusProcessorClient.start();

                    while (running.get()) {
                        System.out.println("Listening to the service bus");
                        Thread.sleep(5000);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }

        });

    }

    public void stop() {
        running.set(false);

        if (serviceBusProcessorClient != null) {
            serviceBusProcessorClient.close();
            serviceBusProcessorClient = null;
        }
        if (executorService != null) {
            executorService.shutdown();
            executorService = null;
        }
        System.out.println("Service bus client stopped.");
    }

    private static void processMessage(ServiceBusReceivedMessageContext context) {
        String body = context.getMessage().getBody().toString();
        System.out.println("[Received Message:] " + body);
    }

    private static void processErrorMessage(ServiceBusErrorContext context) {
        System.err.println("[Error Message:] " + context.getException());
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
