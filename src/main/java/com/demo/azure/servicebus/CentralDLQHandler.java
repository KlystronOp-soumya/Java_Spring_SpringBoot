package com.demo.azure.servicebus;

import com.azure.messaging.servicebus.*;
import java.time.Duration;

public class CentralDLQHandler {

    private static final String CONNECTION_STRING =
            "Endpoint=sb://localhost:5672/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=your_key";
    private static final String DLQ_QUEUE = "central-dlq";

    public static void main(String[] args) {
        handleDLQMessages();
    }

    public static void handleDLQMessages() {
        // Create receiver for central DLQ queue
        ServiceBusProcessorClient dlqProcessor = new ServiceBusClientBuilder()
                .connectionString(CONNECTION_STRING)
                .processor()
                .queueName(DLQ_QUEUE)
                .maxConcurrentCalls(5)
                .processMessage(CentralDLQHandler::processDLQMessage)
                .processError(context -> System.err.println("❌ DLQ error: " + context.getException().getMessage()))
                .buildProcessorClient();

        System.out.println("🚀 Central DLQ Handler started. Listening to: " + DLQ_QUEUE);
        dlqProcessor.start();

        // Keep it running
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void processDLQMessage(ServiceBusReceivedMessageContext context) {
        ServiceBusReceivedMessage message = context.getMessage();
        String body = message.getBody().toString();

        System.out.printf("☠️ [DLQ Handler] Message received from central DLQ: %s%n", body);

        // Log metadata for debugging
        System.out.println("   -> Enqueued Time: " + message.getEnqueuedTime());
        System.out.println("   -> DeadLetterReason: " + message.getDeadLetterReason());
        System.out.println("   -> DeadLetterErrorDescription: " + message.getDeadLetterErrorDescription());

        // ✅ Here you could:
        // 1. Requeue message back to the main topic
        // 2. Archive message to DB or file
        // 3. Send alerts or metrics

        // Example: Requeue to main topic if retryable
        if ("TransientFailure".equalsIgnoreCase(message.getDeadLetterReason())) {
            requeueMessage(body);
        }

        // Mark message as complete (remove from DLQ)
        context.complete();
    }

    private static void requeueMessage(String body) {
        try (ServiceBusSenderClient sender = new ServiceBusClientBuilder()
                .connectionString(CONNECTION_STRING)
                .sender()
                .topicName("inventory-updates")
                .buildClient()) {

            sender.sendMessage(new ServiceBusMessage(body).setSubject("RETRY_FROM_DLQ"));
            System.out.println("🔁 Requeued message to main topic: " + body);
        }
    }
}

