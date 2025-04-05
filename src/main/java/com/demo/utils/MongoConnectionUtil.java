package com.demo.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.util.Optional;

/**
 * A simple class to establish connection with MongoDB Atlas
 * Use the connection builder class to get the connection string
 *
 * */
public class MongoConnectionUtil {

    private transient  MongoClient mongoClient ;
    public MongoClient getConnection(){
        LoggerUtil.info("Trying to get connection");
        ConnectionStringBuilder mongoConnectionBuilder = new ConnectionStringBuilder.Builder()
                .connectionProtocol(PropertiesLoader.getPropValue(PropKeys.MONGO_ATLAS_URL_PROTOCOL.getValue()))
                .userId(System.getenv("mongo.atlas.userid"))
                .password(PasswordUtil.getEncodedPassword(System.getenv("mongo.atlas.password")))
                .clusterUrl(PropertiesLoader.getPropValue(PropKeys.MONGO_ATLAS_CLUSTER_URL.getValue()))
                .clusterName(PropertiesLoader.getPropValue(PropKeys.MONGO_ATLAS_CLUSTER.getValue()))
                .build();

        final String connectionURL = mongoConnectionBuilder.getConnectionURI();

        this.mongoClient = MongoClients.create(connectionURL) ;

        Optional<MongoClient> mongoClientOptional = Optional.of(mongoClient) ;
        LoggerUtil.info("Connection was returned");
        return  mongoClientOptional.orElseThrow(()-> new RuntimeException("Can not get the connection")) ;
    }

    public void close(){
        try{
            LoggerUtil.info("Trying to close the connection"); ;
            if(null!= this.mongoClient)
                this.mongoClient.close();
            LoggerUtil.info("Mongo connection was closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
