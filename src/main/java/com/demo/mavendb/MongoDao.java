package com.demo.mavendb;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;

public class MongoDao {

	private MongoClient mongoClient;

	public MongoDao(final MongoClient mongoClient) {
		// TODO Auto-generated constructor stub
		this.mongoClient = mongoClient;
	}

	public MongoIterable<Document> getAllRecordsInCollection(String collectionName, MongoDatabase mongoDatabase) {
		// TODO Auto-generated method stub
		return mongoDatabase.getCollection(collectionName).find();
	}

	public Boolean insertRecordsIntoCollection(String collectionName, MongoDatabase mongoDatabase,
			List<Document> insertDocumentList) {
		InsertManyResult insertManyResult = null;
		if (!insertDocumentList.isEmpty()) {
			insertManyResult = mongoDatabase.getCollection(collectionName).insertMany(insertDocumentList);
		}

		return insertManyResult.wasAcknowledged();

	}

	public Boolean deleteSingleRecordFromCollection(Bson filter, MongoDatabase mongoDatabase) {
		// TODO Auto-generated method stub
		DeleteResult deleteResult = null;
		try {

			deleteResult = mongoDatabase.getCollection(UtilEnum.COLLECTION_NAME.value).deleteOne(filter);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return deleteResult.wasAcknowledged();
	}

}
