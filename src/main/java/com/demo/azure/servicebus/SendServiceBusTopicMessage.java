package com.demo.azure.servicebus;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

import java.util.Random;

public class SendServiceBusTopicMessage {

    public static void main(String[] args) {
        PropsUtil props = PropsUtil.getInstance();
        String sbConnectionString = props.getProperty("local.servicebus.connection.string");
        String topicName = props.getProperty("local.servicebus.topic.name");
        String subsName = props.getList("local.servicebus.subscribers").stream().filter((subs) -> subs.equals("subscription.simple")).findFirst().get();

        sendServiceBusTopicMessage(sbConnectionString, topicName);
        subscribeServiceBusTopicMessage(sbConnectionString, topicName, subsName);
    }

    private static void sendServiceBusTopicMessage(final String sbConnnectionString, final String sbTopicName) {

        //Client
        ServiceBusClientBuilder clientBuilder = new ServiceBusClientBuilder().connectionString(sbConnnectionString);

        ServiceBusSenderClient sender = clientBuilder.sender().topicName(sbTopicName).buildClient();

        ServiceBusMessage message = new ServiceBusMessage("FromJava, populating topic");
        message.setContentType("application/text");
        message.setSubject("any");
        message.setMessageId("msg-" + new Random().nextLong());

        sender.sendMessage(message);
        System.out.println("Message was sent to the topic");
        sender.close();


    }

    private static void subscribeServiceBusTopicMessage(final String sbConnectionString, final String sbTopicName, final String sbSubsName) {
        ServiceBusProcessorClient serviceBusProcessorClient = null;
        try {
             serviceBusProcessorClient = new ServiceBusClientBuilder()
                    .connectionString(sbConnectionString)
                    .processor()
                    .topicName(sbTopicName)
                    .subscriptionName(sbSubsName)
                    .processMessage(SendServiceBusTopicMessage::processMessage)
                    .processError(SendServiceBusTopicMessage::processErrorMessage)
                     .disableAutoComplete()
                    .buildProcessorClient();
            serviceBusProcessorClient.start();
            System.in.read();
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
           // serviceBusProcessorClient.close();
        }
    }

    private static void processMessage(ServiceBusReceivedMessageContext context) {
        String body = context.getMessage().getBody().toString();
        System.out.println("[Received Message:] " + body);
    }

    private static void processErrorMessage(ServiceBusErrorContext context) {
        System.err.println("[Error Message:] " + context.getException());
    }
}
