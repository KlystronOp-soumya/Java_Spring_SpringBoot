package com.demo.quarkus.jpa.artist.test;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Singleton
@Startup
public class SchemaInitializer {
    @Inject
    DataSource dataSource;

    @PostConstruct
    void init(){
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS t_artists (id BIGINT NOT NULL, name VARCHAR(255) , bio VARCHAR(255), created_date TIMESTAMP , PRIMARY KEY(id))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
