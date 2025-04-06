package com.demo.pipelines;

import com.demo.utils.LoggerUtil;
import com.demo.utils.MongoConnectionUtil;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;

/**
 *
 * Class to implement the aggregation pipelines
 */
public class MflixPipeline {

    private static final Logger LOGGER = LoggerFactory.getLogger(MflixPipeline.class) ;
    private transient MongoConnectionUtil dataSource ;

    public MflixPipeline()
    {
        this.dataSource = new MongoConnectionUtil() ;
    }

    private Consumer<? super Document> printDocuments() {
        return doc -> System.out.println(doc.toJson(JsonWriterSettings.builder().indent(true).build()));
    }

    /**
     * find the 3 most densely populated cities in Texas.
     */
    public  void threeMostPopulatedCitiesInTexas() {
        LoggerUtil.debug(LOGGER , "Running the pipeline...");
        MongoDatabase sampleTraining = this.dataSource.getConnection().getDatabase("sample_training") ;
        MongoCollection<Document> zips = sampleTraining.getCollection("zips") ;
        Bson match = match(eq("state", "TX"));
        Bson group = group("$city", sum("totalPop", "$pop"));
        Bson project = project(fields(excludeId(), include("totalPop"), computed("city", "$_id")));
        Bson sort = sort(descending("totalPop"));
        Bson limit = limit(3);
        List<Document> results = zips.aggregate(List.of(match, group, project, sort, limit)).into(new ArrayList<>());
        System.out.println("==> 3 most densely populated cities in Texas");
        results.forEach(printDocuments());
        LoggerUtil.debug(LOGGER , "Aggregation pipeline ran successfully");
        this.dataSource.close();
    }

}
