package com.demo.azure.servicebus.policyevent;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.demo.azure.servicebus.JsonUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PolicyEventProducer {

    private static final Logger LOG = Logger.getLogger(PolicyEventProducer.class.getName());

    private final ServiceBusSenderClient sender;

    public PolicyEventProducer(String connectionString, String queue) {
        this.sender = new ServiceBusClientBuilder().connectionString(connectionString)
                .sender()
                .queueName(queue)
                .buildClient();
    }

    public void sendPolicyCreatedEvent(PolicyCreatedEvent policyCreatedEvent) throws Exception {

        String jsonPayload = JsonUtil.toJson(policyCreatedEvent);

        ServiceBusMessage message = new ServiceBusMessage(jsonPayload);
        // always set the metadata
        message.setContentType("application/json");
        message.setSubject(policyCreatedEvent.getEventType().getValue());
        message.setMessageId(policyCreatedEvent.getEventId());

        message.getApplicationProperties().put("eventType", EventType.POLICY_CREATED.getValue());
        message.getApplicationProperties().put("policyNumber", policyCreatedEvent.getPolicy().getPolicyNumber());

        sender.sendMessage(message);
        LOG.log(Level.INFO, "policy created event submitted successfully");
        sender.close();
    }

}
