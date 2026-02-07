import { InvocationContext, output, ServiceBusQueueHandler } from "@azure/functions";
import { sendMessageToTopic } from "./topicClient";


export const queue1Handler:ServiceBusQueueHandler = (message:String, context:InvocationContext) =>{

        context.log('Service bus queue function processed message:', message);
        context.log('EnqueuedTimeUtc =', context.triggerMetadata?.enqueuedTimeUtc);
        context.log('DeliveryCount =', context.triggerMetadata?.deliveryCount);
        context.log('MessageId =', context.triggerMetadata?.messageId);

        //sendMessageToTopic(message);
};