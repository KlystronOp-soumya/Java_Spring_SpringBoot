package com.demo.azure.servicebus;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

/**
 * Hello world!
 *
 */
public class SendServiceBusMessage 
{
    private transient static final String SB_CONNECTION="Endpoint=sb://127.0.0.1:5672/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=DUMMY_KEY_FOR_EMULATOR;UseDevelopmentEmulator=true;" ;
    private static final String QUEUE_NAME = "queue.1";

    public static void main( String[] args )
    {
        ServiceBusClientBuilder builder = new ServiceBusClientBuilder().connectionString(SB_CONNECTION) ;

        ServiceBusSenderClient senderClient = builder.sender().queueName(QUEUE_NAME).buildClient() ;

        final String messageString = "Hello,from service bus" ;

        senderClient.sendMessage(new ServiceBusMessage(messageString));

        System.out.println("Message was sent");
        senderClient.close();
    }

    private static void consumeServiceBusMessage(){
// Receiving a message
ServiceBusClientBuilder builder = new ServiceBusClientBuilder()
            .connectionString(SB_CONNECTION);
        ServiceBusReceiverClient receiverClient = builder
            .receiver()
            .queueName(QUEUE_NAME)
            .buildClient();

        receiverClient.receiveMessages(1).forEach(msg -> {
            System.out.println("Received: " + msg.getBody().toString());
            receiverClient.complete(msg);
        });
        receiverClient.close();
    }
}
