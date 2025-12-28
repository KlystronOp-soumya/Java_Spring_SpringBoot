package com.demo.azure.servicebus.policyevent;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.demo.azure.servicebus.JsonUtil;
import com.demo.azure.servicebus.PropsUtil;

public class PolicyEventConsumer {

    public static void main(String[] args) {
        ServiceBusProcessorClient processor = new ServiceBusClientBuilder()
                .connectionString(PropsUtil.getInstance().getProperty("local.servicebus.connection.string"))
                .processor()
                .queueName(EventType.POLICY_CREATED.getValue())
                .processMessage(context ->  {
                    try {
                            String json = context.getMessage().getBody().toString();
                            PolicyCreatedEvent event = JsonUtil.fromJson(json, PolicyCreatedEvent.class);

                        System.out.println(json);

                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                })
                .processError(serviceBusErrorContext -> System.err.println(serviceBusErrorContext.getException()))
                .buildProcessorClient();

        processor.start();
        processor.close();
    }
}
