package com.demo.dao;

import com.demo.utils.MongoConnectionUtil;
import com.demo.utils.PropKeys;
import com.demo.utils.PropertiesLoader;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class MongoDAO {
    private transient MongoConnectionUtil dataSource;
    private transient MongoDatabase mongoDatabase;

    public MongoDAO() {
        this.dataSource = new MongoConnectionUtil();
       // initDB();
    }

    private void initDB() {
        try {
            this.mongoDatabase = this.dataSource.getConnection().getDatabase(PropertiesLoader.getPropValue(PropKeys.MONGO_ATLAS_DB_NAME.getValue()));
        } catch (Exception e) {
            throw new RuntimeException("Can not create database :: Cause" + e.getMessage());

        }
    }

    public Document findMovieByTitle(final String fieldName, final String value) {
        Optional<Document> documentOptional ;
        initDB();
        try{
            MongoCollection<Document> movies = mongoDatabase.getCollection("movies");

            documentOptional = Optional.ofNullable(  movies.find(eq(fieldName , value)).first() );
        } catch (Exception e) {
            throw new RuntimeException("can not fetch details->" + e.getMessage());

        }finally {
            this.dataSource.close();
        }
        return documentOptional.orElse(null);
    }


}
