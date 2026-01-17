package com.demo.azure.servicebus.policyevent;

import com.azure.messaging.servicebus.*;
import com.azure.messaging.servicebus.models.SubQueue;

public class DlqRedriveClient {

    public static void main(String[] args) {

        ServiceBusClientBuilder builder =
                new ServiceBusClientBuilder()
                        .connectionString("");

        ServiceBusReceiverClient dlqReceiver =
                builder.receiver()
                        .queueName("policy.created")
                        .subQueue(SubQueue.DEAD_LETTER_QUEUE)
                        .buildClient();

        ServiceBusSenderClient sender =
                builder.sender()
                        .queueName("policy.created")
                        .buildClient();

        var messages = dlqReceiver.receiveMessages(10);

        for (ServiceBusReceivedMessage msg : messages) {

            System.out.println("Re-driving message: " + msg.getMessageId());

            ServiceBusMessage newMsg =
                    new ServiceBusMessage(msg.getBody());

            newMsg.setContentType(msg.getContentType());
            newMsg.setSubject(msg.getSubject());

            sender.sendMessage(newMsg);
            dlqReceiver.complete(msg);
        }

        dlqReceiver.close();
        sender.close();
    }
}
