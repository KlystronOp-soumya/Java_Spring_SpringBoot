package com.demo.dao;

import com.demo.utils.LoggerUtil;
import com.demo.utils.MongoConnectionUtil;
import com.demo.utils.PropKeys;
import com.demo.utils.PropertiesLoader;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class MongoDAO {
    /*The Logger*/
    private static  final Logger LOGGER = LoggerFactory.getLogger(MongoDAO.class) ;
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
        LoggerUtil.info(LOGGER , "Trying to find out movie by name: " + value);
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
        LoggerUtil.info(LOGGER , "Returned result from Database: " + value);
        return documentOptional.orElse(null);
    }


}
