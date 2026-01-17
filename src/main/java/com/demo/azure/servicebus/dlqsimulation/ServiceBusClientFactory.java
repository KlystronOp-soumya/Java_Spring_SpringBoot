package com.demo.azure.servicebus.dlqsimulation;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.models.SubQueue;
import com.demo.azure.servicebus.PropsUtil;

public final class ServiceBusClientFactory {

    private static PropsUtil props;

    static {
        props = PropsUtil.getInstance();
    }

    private ServiceBusClientFactory() {}

    public static ServiceBusSenderClient sender() {

        return new ServiceBusClientBuilder()
                .connectionString(props.getProperty("local.servicebus.connection.string"))
                .sender()
                .queueName(props.getProperty("local.servicebus.queue.name"))
                .buildClient();
    }

    public static ServiceBusReceiverClient receiver() {

        return new ServiceBusClientBuilder()
                .connectionString(props.getProperty("local.servicebus.connection.string"))
                .receiver()
                .queueName(props.getProperty("local.servicebus.queue.name"))
                .buildClient();
    }

    public static ServiceBusReceiverClient dlqReceiver() {
        return new ServiceBusClientBuilder()
                .connectionString(props.getProperty("local.servicebus.connection.string"))
                .receiver()
                .queueName(props.getProperty("local.servicebus.queue.name"))
                .subQueue(SubQueue.DEAD_LETTER_QUEUE)
                .buildClient();
    }

}
