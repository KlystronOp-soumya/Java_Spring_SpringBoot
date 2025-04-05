package com.demo;

import com.demo.dao.MongoDAO;
import com.demo.utils.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.MongoClient.*;
import static com.mongodb.client.model.Filters.eq;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       /* final String mongoURI = "mongodb+srv://shivadeep064:So%4013608@cluster-practice.infjppq.mongodb.net/?retryWrites=true&w=majority&appName=Cluster-Practice" ;

        try ( MongoClient mongoClient = MongoClients.create(mongoURI) ){
            String encodedUrl = URLEncoder.encode("So@13608", StandardCharsets.UTF_8) ;
            System.out.println(encodedUrl);

            MongoDatabase mongoDatabase = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = mongoDatabase.getCollection("movies");

            Document document = collection.find(eq("title", "Back to the Future")).first();
            if (document != null) {
                System.out.println(document.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        LoggerUtil.info("Application was started");
        try{
            MongoDAO mongoDAO = new MongoDAO() ;
            System.out.println(mongoDAO.findMovieByTitle("title" , "Back to the Future").toJson() );
            System.out.println(mongoDAO.findMovieByTitle("title" , "Back to the Future").toJson() );
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }




    }
}