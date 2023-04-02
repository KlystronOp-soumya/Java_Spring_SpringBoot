package com.demo.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ConnectionUtil {

	private transient static MongoClient mongoClient;

	public static MongoClient getMongoClient() {
		try {

			mongoClient = MongoClients.create("mongodb://localhost:27017");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mongoClient;
	}

	public static void closeMongoConnection() {
		try {
			mongoClient.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
