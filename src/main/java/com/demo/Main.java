package com.demo;

import com.demo.dao.MongoDAO;
import com.demo.pipelines.MflixPipeline;
import com.demo.utils.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

import static com.mongodb.client.MongoClient.*;
import static com.mongodb.client.model.Filters.eq;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static  final Logger LOGGER = LoggerFactory.getLogger(Main.class) ;
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
        LoggerUtil.info(LOGGER , "Started application...");
        try{
            MongoDAO mongoDAO = new MongoDAO() ;
            System.out.println(mongoDAO.findMovieByTitle("title" , "Back to the Future").toJson() );

            MflixPipeline pipeline = new MflixPipeline() ;
            pipeline.threeMostPopulatedCitiesInTexas();

        } catch (Exception e) {
            LoggerUtil.error( LOGGER,"Error occurred ===> " + e );
        }finally {

        }




    }
}