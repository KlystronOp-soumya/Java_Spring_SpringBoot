package com.demo.azure;

import com.demo.azure.model.ResponseWrapper;
import com.demo.azure.model.UserAction;
import com.demo.azure.utils.DateTimeUtils;
import com.google.gson.Gson;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.QueueOutput;
import com.microsoft.azure.functions.annotation.QueueTrigger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class FunctionApp {

    /*
     * A simple test http trigger function
     */
    @FunctionName("testHttpTrigger")
    public HttpResponseMessage processHttpTrigger(
            @HttpTrigger(name = "req", methods = {
                    HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        var logger = context.getLogger();
        logger.info("[Test] received request");

        ResponseWrapper responseWrapper = new ResponseWrapper();
        final String respSentAt;
        final String reqReceivedAt = LocalDateTime.now().format(DateTimeFormatter.ISO_TIME);

        String reqBodyJson = request.getBody().orElse("");

        if (!reqBodyJson.isEmpty()) {
            respSentAt = LocalDateTime.now().format(DateTimeFormatter.ISO_TIME);
            Gson gson = new Gson();
            final UserAction userAction = gson.fromJson(reqBodyJson, UserAction.class);
            responseWrapper.setUserAction(userAction);
            responseWrapper.setReqReceivedTime(reqReceivedAt);
            responseWrapper.setResSendTime(respSentAt);

            return request.createResponseBuilder(HttpStatus.OK).body(gson.toJson(responseWrapper)).build();
        } else {
            respSentAt = LocalDateTime.now().format(DateTimeFormatter.ISO_TIME);

            responseWrapper.setUserAction(null);
            responseWrapper.setReqReceivedTime(reqReceivedAt);
            responseWrapper.setResSendTime(respSentAt);
            return request.createResponseBuilder(HttpStatus.NOT_FOUND).body(responseWrapper).build();
        }
    }

    /**
     * This method receives a message from http request
     * then pushes a message to the queue
     * 
     * 
     */
    @FunctionName("pushMsgToQueue")
    public HttpResponseMessage httpTrigQueueProcess(
            @HttpTrigger(name = "req", methods = {
                    HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,

            @QueueOutput(name = "outputMessage", queueName = "myqueue",connection = "AzureWebJobsStorage") OutputBinding<UserAction> outputBinding,
            
            final ExecutionContext context) {
        var logger = context.getLogger();
        Gson gson = new Gson();
        ResponseWrapper responseWrapper = new ResponseWrapper();
        final String reqRcvdAt = DateTimeUtils.getISOTime();
        final String reqBodyMsg = request.getBody().orElse("");
        logger.info("[LOGS]Request received:: pushin into queue");
        try {
            if (!reqBodyMsg.isBlank()) {
                // get the json into object
                final UserAction userActReq = gson.fromJson(reqBodyMsg, UserAction.class);
                responseWrapper.setUserAction(userActReq);
                responseWrapper.setReqReceivedTime(reqRcvdAt);
                responseWrapper.setResSendTime(DateTimeUtils.getISOTime());
                outputBinding.setValue(userActReq);
                return request.createResponseBuilder(HttpStatus.CREATED).body(responseWrapper).build();
            } else {
                responseWrapper.setUserAction(null);
                responseWrapper.setReqReceivedTime(reqRcvdAt);
                responseWrapper.setResSendTime(DateTimeUtils.getISOTime());
                return request.createResponseBuilder(HttpStatus.EXPECTATION_FAILED).body(responseWrapper).build();
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
            // Connect to Azurite queue
            /*
             * CloudStorageAccount storageAccount =
             * CloudStorageAccount.parse("UseDevelopmentStorage=true");
             * CloudQueueClient queueClient = storageAccount.createCloudQueueClient();
             * CloudQueue queue = queueClient.getQueueReference("myqueue-items");
             * queue.createIfNotExists();
             * 
             * // Add message with 10-second visibility delay
             * CloudQueueMessage message = new CloudQueueMessage(queueMessage);
             * queue.addMessage(message, null, 10, null); // delay = 10 seconds
             */
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(e.getMessage()).build();
        }
    }

    /* 
     * Method to consume the queue message
     * 
     * 
    */
    @FunctionName("consumeFromQueue")
    public void processQueueConsumption(
            @QueueTrigger(name = "msg", queueName = "myqueue",connection = "AzureWebJobsStorage") UserAction message,
            final ExecutionContext context) {
        context.getLogger().info("======QueueTrigger was invoked============\n");
        context.getLogger().info("======> [Consumed message] : " + message);
    }
}
