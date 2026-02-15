package com.demo.azure.servicebus.policyevent;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.demo.azure.servicebus.JsonUtil;
import com.demo.azure.servicebus.PropsUtil;

public class SendPolicyToDLQ {

    //this is a consumer class which fails to read data from the service bus
    public static void main(String[] args) {
        ServiceBusProcessorClient processor = new ServiceBusClientBuilder()
                .connectionString(PropsUtil.getInstance().getProperty("local.servicebus.connection.string"))
                .processor()
                .queueName(EventType.POLICY_CREATED.getValue())
                .processMessage(context ->  {
                        ServiceBusReceivedMessage message = context.getMessage();
                        throw new RuntimeException("processing failure");
                })
                .processError(serviceBusErrorContext -> System.err.println(serviceBusErrorContext.getException()))
                .buildProcessorClient();

        processor.start();
        try {
            Thread.sleep(5 * 60 * 1000); // keep app alive
        } catch (InterruptedException ignored) {
        } finally {
            processor.close();
        }
    }
}
