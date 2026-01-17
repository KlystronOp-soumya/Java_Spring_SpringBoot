package com.demo.azure.servicebus.dlqsimulation;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;

public class DlqRetryProcessor {

    public static void main(String[] args) {

        try (var dlqReceiver = ServiceBusClientFactory.dlqReceiver();
             var sender = ServiceBusClientFactory.sender()) {

            for (ServiceBusReceivedMessage msg : dlqReceiver.receiveMessages(10)) {

                System.out.println("Reprocessing DLQ message: " + msg.getBody());

                sender.sendMessage(new ServiceBusMessage(msg.getBody()));
                dlqReceiver.complete(msg);
            }
        }
    }

}

