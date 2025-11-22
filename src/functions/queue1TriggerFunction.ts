import {app, ServiceBusQueueFunctionOptions } from "@azure/functions";
import { queue1Handler } from "./queue1Handler";

const queueOptions:ServiceBusQueueFunctionOptions={
 connection:"ServiceBusConnectionString",
 queueName:"queue.1",
 handler: queue1Handler
}
app.serviceBusQueue("queue1ServiceBusTrigger", queueOptions);