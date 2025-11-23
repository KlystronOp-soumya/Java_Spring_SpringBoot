import { ServiceBusClient } from "@azure/service-bus";
import dotenv from "dotenv";

dotenv.config();

export async function sendMessageToTopic(message:String) {

    const connectionString = process.env.SERVICE_BUS_CONNECTION??(()=>{ throw new Error("Connection string can not be blank")}) ();

    const topicName:string = process.env.TOPIC_NAME??(()=>{throw new Error("There must be a topic name")})();

    const client = new ServiceBusClient(connectionString);
    const sender = client.createSender(topicName);

    try {
        const payload = {
            body: message,
            contentType: "application/text",
            subject: "any"
        };

        console.log(`sending message to the topic ${message}`);

        await sender.sendMessages(payload);
        
    } catch (error) {
        console.error("Error sending message: " , error);
    }finally{
        await sender.close();
        await client.close();
    }
    
}
