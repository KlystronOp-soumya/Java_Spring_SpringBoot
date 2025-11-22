package com.demo.azure.servicebus;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

/**
 * Hello world!
 *
 */
public class SendServiceBusMessage {
    private transient static final String SB_CONNECTION = "Endpoint=sb://127.0.0.1:5672;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=DUMMY_KEY_FOR_EMULATOR;UseDevelopmentEmulator=true;";

    private static final String QUEUE_NAME = "queue.1";

    public static void main(String[] args) {
        ServiceBusClientBuilder builder = new ServiceBusClientBuilder().connectionString(SB_CONNECTION);

        ServiceBusSenderClient senderClient = builder
                .sender()
                .queueName(QUEUE_NAME)
                .buildClient();

        final String messageString = "Hello,from service bus java program!Testing the serverless trigger";


        try {
           senderClient.sendMessage(new ServiceBusMessage(messageString));

            System.out.println("Message was sent");
            // Receiving a message
           /* ServiceBusReceiverClient receiverClient = builder
                    .receiver()
                    .queueName(QUEUE_NAME)
                    .buildClient();

            System.out.println("Trying to read the message");
            receiverClient.receiveMessages(1).forEach(msg -> {
                System.out.println("Received: " + msg.getBody().toString());
                receiverClient.complete(msg);

            });
            receiverClient.close(); */
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            senderClient.close();

        }
    }

    private static void consumeServiceBusMessage() {
        // Receiving a message
        System.out.println("Consuming ServiceBusMessage" + "\n".repeat(5));
        ServiceBusClientBuilder builder = new ServiceBusClientBuilder()
                .connectionString(SB_CONNECTION);
        ServiceBusReceiverClient receiverClient = builder
                .receiver()
                .queueName(QUEUE_NAME)
                .buildClient();
        try {
            receiverClient.receiveMessages(1).forEach(msg -> {
                System.out.println("Received: " + msg.getBody().toString());
                receiverClient.complete(msg);
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            receiverClient.close();
        }

    }

    // send a message to the topic
    private static void sendServiceBusMessage() {
        ServiceBusClientBuilder builder = new ServiceBusClientBuilder();
        builder.connectionString(SB_CONNECTION);
        ServiceBusSenderClient senderClient = builder.sender().queueName(QUEUE_NAME).buildClient();
    }

}
