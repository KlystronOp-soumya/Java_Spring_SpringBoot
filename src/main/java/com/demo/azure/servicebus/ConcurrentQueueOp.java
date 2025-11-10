package com.demo.azure.servicebus;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * This applies the multithreading to interact with the Azure service bus
 * */
public class ConcurrentQueueOp {
    private static final String SB_CONNECTION = "Endpoint=sb://127.0.0.1:5672;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=DUMMY_KEY_FOR_EMULATOR;UseDevelopmentEmulator=true;";

    private static final String SEND_QUEUE_NAME = "queue.1";

    private static final Object lock = new Object();

    private static final AtomicBoolean messageProcessed = new AtomicBoolean(true);

    public static void main(String[] args) {

        Thread senderThread = new Thread(new SendQueue());
        Thread receiverThread = new Thread(new ReceiveQueue());
        try {
            senderThread.start();
            receiverThread.start();

            senderThread.join();
            receiverThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            senderThread.stop();
            receiverThread.stop();
            System.exit(0);
        }

    }

    private static class ReceiveQueue implements Runnable {

        @Override
        public void run() {

            try (ServiceBusReceiverClient receiverClient = new ServiceBusClientBuilder()
                    .connectionString(SB_CONNECTION)
                    .receiver()
                    .queueName(SEND_QUEUE_NAME)
                    .buildClient()
            ) {
                while (true) {
                    synchronized (lock) {
                        if (messageProcessed.get()) {
                            lock.wait();//stop this thread and wait for sender to publish
                        }
                        receiverClient.receiveMessages(1, Duration.ofSeconds(5))
                                .stream()
                                .findFirst()
                                .ifPresent(serviceBusReceivedMessage -> {
                                    System.out.println(serviceBusReceivedMessage.getBody().toString());
                                    receiverClient.complete(serviceBusReceivedMessage);
                                });
                        messageProcessed.set(true);
                        lock.notify();//notifies the producer
                    }


                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    private static class SendQueue implements Runnable {

        @Override
        public void run() {

            try (ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                    .connectionString(SB_CONNECTION)
                    .sender()
                    .queueName(SEND_QUEUE_NAME)
                    .buildClient()) {
                for (int i = 0; i <= 5; i++) {

                    synchronized (lock) {
                        while (!messageProcessed.get()) {
                            lock.wait();//wait until receiver reads the message
                        }
                        String messageBody = "Message Body" + i;
                        ServiceBusMessage sbMessage = new ServiceBusMessage(messageBody);
                        senderClient.sendMessage(sbMessage);
                        System.out.println("Message sent to sender");

                        messageProcessed.set(false);//mark as unread
                        lock.notify();//notify the receiver
                    }


                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {

            }
        }
    }


}
