package com.demo.mongodb;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		MongoClient mongoClient = ConnectionUtil.getMongoClient();
		MongoDao mongoDao = new MongoDao(mongoClient);
		MongoService mongoService = new MongoService(mongoDao, mongoClient);
		System.out.println("Connected database: " + mongoService.getDatabaseName());
		System.out.println("All collections: ");
		mongoService.getAllDatabase();

		Document inventoryEntry = new Document("_id", new ObjectId());
		Document parsedDocument = Document.parse("{  \"item\" : \"A4 Pages\" ,\r\n" + "   \"qty\" :120 ,  \r\n"
				+ "   \"size\" : { \"h\":14 , \"w\":21 , \"uom\" : \"cm\" } ,\r\n" + "   \"status\" : \"A\"  ,\r\n"
				+ "   \"tags\" :[ \"blank\" , \"red\" ] ,\r\n" + "   \"dim_cm\" : [ 0  14  1  21]\r\n" + "  }");
		// if (mongoService.insertRecordsInCollection(UtilEnum.COLLECTION_NAME.value,
		// Arrays.asList(parsedDocument)))
		// System.out.println("Document inserted successfully");

		mongoService.showRecordsInCollection(UtilEnum.COLLECTION_NAME.value);
		// Bson filterBson = Filters.eq("item", "A4 Pages");
		// System.out.println("Record was deleted: " +
		// mongoService.deleteSingleRecord(filterBson));
		ConnectionUtil.closeMongoConnection();
	}
}
