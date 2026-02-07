import {app, ServiceBusQueueFunctionOptions, ServiceBusQueueTriggerOptions } from "@azure/functions";
import { policyCreatedEventHandler } from "./policyCreatedEventHandler";

const queueOptions:ServiceBusQueueFunctionOptions = {
    connection: "ServiceBusConnectionString",
    queueName:"policy.created",
    handler: policyCreatedEventHandler
}

app.serviceBusQueue("policyCreatedServiceBusTrigger", queueOptions);