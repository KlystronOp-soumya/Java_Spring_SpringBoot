package com.demo.azure.servicebus;

import com.demo.azure.servicebus.concurrent.TopicSubscriberLatched;

import java.util.concurrent.CountDownLatch;

/**
 * This class is used to subscribe a topic and read from it
 * The topic is being populated by the service bus trigger from a separate system
 *
 */
public class TopicSubscriber {
    private static final String CONNECTION_STRING =
            "Endpoint=sb://localhost:5672/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=your_key";

    public static void main(String[] args) {

        TopicSubscriberService topicSubscriberService = new TopicSubscriberService(CONNECTION_STRING, "topic.1", "subscription.simple");
        System.out.println("[Starting service]Subscribing to topic 1");
        topicSubscriberService.start();

        System.out.println("Subscriber running in background… press ENTER to stop.");
        new java.util.Scanner(System.in).nextLine();

        topicSubscriberService.stop();


    }

    private static void latched() {
        TopicSubscriberLatched topicSubscriberLatched = new TopicSubscriberLatched(CONNECTION_STRING,"topic.1", "subscription.1");
        topicSubscriberLatched.start();
        CountDownLatch latch = new CountDownLatch(1);
        try{
            latch.await();
        }catch (InterruptedException e){
            latch.countDown();
        }
        topicSubscriberLatched.stop();
    }
}
