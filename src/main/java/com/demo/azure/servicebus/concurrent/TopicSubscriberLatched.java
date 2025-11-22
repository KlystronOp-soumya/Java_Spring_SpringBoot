package com.demo.azure.servicebus.concurrent;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TopicSubscriberLatched {

    private final String connectionString;
    private final String topicName;
    private final String subscriptionName;

    private ServiceBusProcessorClient serviceBusProcessorClient;
    private ExecutorService executorService;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public TopicSubscriberLatched(String connectionString, String topicName, String subscriptionName) {
        this.connectionString = connectionString;
        this.topicName = topicName;
        this.subscriptionName = subscriptionName;
    }

    public void start(){
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this::runProcessor);
    }

    private void runProcessor() {
        try{
            this.serviceBusProcessorClient = new ServiceBusClientBuilder().connectionString(this.connectionString).processor().topicName(this.topicName).subscriptionName(this.subscriptionName).processMessage(this::handleMessage).processError(this::handleError).buildProcessorClient();
            serviceBusProcessorClient.start();

            countDownLatch.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            stop();
        }
    }

    private  void handleMessage(ServiceBusReceivedMessageContext ctx) {

        String body = ctx.getMessage().getBody().toString();
        System.out.println("Received message: " + body);

        this.countDownLatch.countDown();
    }

    private void handleError(ServiceBusErrorContext ctx) {
        System.err.println("❌ Error: " + ctx.getException());
    }

    public void stop() {
        if (serviceBusProcessorClient != null) {
            serviceBusProcessorClient.close();
        }
        if (this.executorService != null) {
            this.executorService.shutdownNow();
        }
        System.out.println("Subscriber stopped.");
    }


}
