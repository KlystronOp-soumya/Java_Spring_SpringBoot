package com.demo.utils;

/**
 *
 * Simple builder class to build the connection string
 *
 * */
public class ConnectionStringBuilder {

    private String connectionUrl;
    private String userId;
    private String password;
    private String databaseName; // Optional field
    private String databaseType ;

    private ConnectionStringBuilder(){} ;

    public static class Builder{
        private String connectionUrl;
        private String userId;
        private String password;
        private String databaseName; // Optional field
        private String databaseType ;

        //put the mandatory fields using the constructor
        public Builder(final String connectionUrl , final String userId , final String password , final String databaseName , final String databaseType ){

        }

    }
}
