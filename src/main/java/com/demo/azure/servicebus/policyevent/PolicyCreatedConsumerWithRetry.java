package com.demo.azure.servicebus.policyevent;

import com.azure.messaging.servicebus.*;
import com.azure.messaging.servicebus.models.CompleteOptions;
import com.azure.messaging.servicebus.models.DeadLetterOptions;
import com.demo.azure.servicebus.PropsUtil;

public class PolicyCreatedConsumerWithRetry {

    public static void main(String[] args) {

        ServiceBusProcessorClient processor =
                new ServiceBusClientBuilder()
                        .connectionString(PropsUtil.getInstance().getProperty("local.servicebus.connection.string"))
                        .processor()
                        .queueName("policy.created")
                        .maxConcurrentCalls(1)
                        .processMessage(context -> {

                            ServiceBusReceivedMessage msg =
                                    context.getMessage();

                            try {
                                processPolicy(msg);

                                // Success
                               context.complete();

                            } catch (TransientProcessingException ex) {

                                System.out.println("Transient failure. Will retry. DeliveryCount="
                                        + msg.getDeliveryCount());

                                // abandon → retry
                                context.abandon();

                            } catch (PermanentProcessingException ex) {

                                System.out.println("Permanent failure. Sending to DLQ.");

                                context.deadLetter(new DeadLetterOptions().setDeadLetterReason("ValidationError"));
                            }
                        })
                        .processError(errorContext ->
                                System.err.println(errorContext.getException()))
                        .buildProcessorClient();

        processor.start();

        keepAlive();
    }

    private static void processPolicy(ServiceBusReceivedMessage msg) {

        int delivery = Math.toIntExact(msg.getDeliveryCount());

        // Example behavior
        if (delivery < 3) {
            throw new TransientProcessingException("Temporary DB failure");
        }

        if (delivery == 3) {
            throw new PermanentProcessingException("Invalid policy data");
        }

        System.out.println("Policy processed successfully.");
    }

    private static void keepAlive() {
        try {
            Thread.sleep(10 * 60 * 1000);
        } catch (InterruptedException ignored) {}
    }
}
