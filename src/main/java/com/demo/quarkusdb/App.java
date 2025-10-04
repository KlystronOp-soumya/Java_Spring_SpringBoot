package com.demo.quarkusdb;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Path("/api/v1")
public class App {

    @Inject
    private  transient DataSource dataSource ;

    @GET
    @Produces(value = MediaType.TEXT_PLAIN)
    public void testDBConnection(){
        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY , ResultSet.CONCUR_READ_ONLY) ;
            ResultSet rs = stmt.executeQuery("select * from customer limit 10 ") ;
            while (rs.next()){
                System.out.println(rs.getString("first_name") +" "+ rs.getString("last_name"));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }


}
