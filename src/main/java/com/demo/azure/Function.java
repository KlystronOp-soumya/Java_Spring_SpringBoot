package com.demo.azure;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;
import com.microsoft.azure.functions.annotation.ServiceBusTopicTrigger;
/* https://github.com/Azure/azure-service-bus-emulator-installer/blob/main/Sample-Code-Snippets/Java/azure-service-bus-emulator-console-sample/src/main/java/com/contoso/org/Main.java */
/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    @FunctionName("QueueConsumer")
    public void consumeQueue(
        @ServiceBusQueueTrigger(
            name = "message",
            queueName = "queue.1",
            connection = "ServiceBusConnection"
        ) String message,
        final ExecutionContext context
    ) {
        context.getLogger().info("=============QUEUE=============\n\n");
        context.getLogger().info("QueueConsumer received: " + message);
    }

    @FunctionName("TopicConsumer")
    public void consumeTopic(
        @ServiceBusTopicTrigger(
            name = "message",
            topicName = "topic.1",
            subscriptionName = "subscription.2",
            connection = "ServiceBusConnection"
        ) String message,
        final ExecutionContext context
    ) {
        context.getLogger().info("=============TOPIC=============\n\n");
        context.getLogger().info("TopicConsumer received: " + message);
    }


}
