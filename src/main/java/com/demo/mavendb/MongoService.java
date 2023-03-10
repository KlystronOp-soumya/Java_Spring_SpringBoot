package com.demo.mavendb;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoService implements MongoServiceIntf {

	private MongoDao mongoDao;
	private MongoClient mongoClient;
	private static MongoDatabase mongoDatabase;

	public MongoService(final MongoDao mongoDao, final MongoClient mongoClient) {
		// TODO Auto-generated constructor stub
		this.mongoClient = mongoClient;
		this.mongoDao = mongoDao;
		mongoDatabase = this.mongoClient.getDatabase(UtilEnum.DB_NAME.value);
	}

	public String getDatabaseName() {
		return mongoDatabase.getName();
	}

	@Override
	public Boolean createDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDatabase() {
		// TODO Auto-generated method stub
		try {
			mongoDatabase.drop();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public Boolean deleteCollection(String collectionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertRecordsInCollection(String collectionName, List<Document> insertDocumentList) {
		// TODO Auto-generated method stub

		return this.mongoDao.insertRecordsIntoCollection(collectionName, mongoDatabase, insertDocumentList);
	}

	@Override
	public Boolean updateRecordsInCollection(String collectionName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean deleteSingleRecord(Bson filter) {
		return this.mongoDao.deleteSingleRecordFromCollection(filter, mongoDatabase);
	}

	@Override
	public void showRecordsInCollection(String collectionName) {
		this.mongoDao.getAllRecordsInCollection(collectionName, mongoDatabase)
				.forEach(eachRecord -> System.out.println(eachRecord.toJson()));

	}

	@Override
	public MongoCollection<Document> getCollection() {
		// TODO Auto-generated method stub

		return mongoDatabase.getCollection(UtilEnum.COLLECTION_NAME.value);
	}

	public void getAllDatabase() {

		this.mongoClient.listDatabaseNames().forEach(System.out::println);
	}

}
