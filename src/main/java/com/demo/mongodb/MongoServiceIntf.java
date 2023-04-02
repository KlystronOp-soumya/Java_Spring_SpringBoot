package com.demo.mongodb;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public interface MongoServiceIntf {

	String getDatabaseName();

	Boolean createDatabase();

	void deleteDatabase();

	MongoCollection<Document> getCollection();

	Boolean deleteCollection(String collectionName);

	Boolean updateRecordsInCollection(String collectionName);

	void showRecordsInCollection(String collectionName);

	Boolean insertRecordsInCollection(String collectionName, List<Document> insertDocumentList);
}
