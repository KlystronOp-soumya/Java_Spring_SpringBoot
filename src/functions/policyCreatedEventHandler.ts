import { InvocationContext, ServiceBusQueueHandler } from "@azure/functions";
import { PolicyCreatedEvent } from "../types/PolicyCreatedEvent";
import { TypedServiceBusHandler } from "../types/TypedServiceBusHandler";

export const policyCreatedEventHandler:TypedServiceBusHandler<PolicyCreatedEvent> = async (message: PolicyCreatedEvent, context: InvocationContext) : Promise<void> => {
    
     context.log("Policy Created Event Received");

    context.log(`Message ID: ${context.invocationId}`);
    context.log(`Payload: ${message}}`);
    context.log(`converted as: ${JSON.stringify(message)}`);
    
}

