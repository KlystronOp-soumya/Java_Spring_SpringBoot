package com.demo.utils;

/**
 * Simple builder class to build the connection string
 */
public class ConnectionStringBuilder {
    private String connectionProtocol;
    private String connectionUrl;
    private String userId;
    private String password;
    private String databaseName; // Optional field
    private String databaseType;
    private String clusterName;
    private String clusterURL;

    private ConnectionStringBuilder() {
    }

    ;

    /**
     * Returns the connection URI to establish connetion with Atlas cluster
     */
    public String getConnectionURI() {
        StringBuilder stringBuilder = new StringBuilder();
        String creds = PropertiesLoader.getPropValue(PropKeys.MONGO_ATLAS_CREDS.value);
        creds = creds.replace("$1", this.userId);
        creds = creds.replace("$2", this.password);
        stringBuilder.append(this.connectionProtocol);
        stringBuilder.append(creds);
        stringBuilder.append(this.clusterURL);
        stringBuilder.append(this.clusterName) ;

        return stringBuilder.toString();
    }

    public static class Builder {
        private String connectionProtocol;
        private String connectionUrl;
        private String userId;
        private String password;
        private String databaseName;// Optional field
        private String databaseType;
        private String clusterName;
        private String clusterURL;

        public Builder() {
        }

        //put the mandatory fields using the constructor
        public Builder(final String connectionProtocol, final String connectionUrl, final String userId, final String password, final String databaseName, final String databaseType ,String clusterName ,
        String clusterURL ) {
            this.connectionProtocol = connectionProtocol;
            this.connectionUrl = connectionUrl;
            this.userId = userId;
            this.password = password;
            this.databaseName = databaseName;
            this.databaseType = databaseType;
            this.clusterName = clusterName ;
            this.clusterURL = clusterURL ;
        }

        public Builder connectionProtocol(String connectionProtocol) {
            this.connectionProtocol = connectionProtocol;
            return this ;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this ;
        }

        public Builder password(String password) {
            this.password = password;
            return this ;
        }

        public Builder databaseName(String databaseName) {
            this.databaseName = databaseName;
            return this ;
        }

        public Builder databaseType(String databaseType) {
            this.databaseType = databaseType;
            return this ;
        }

        public Builder connectionUrl(String connectionUrl) {
            this.connectionUrl = connectionUrl;
            return this ;
        }
        public Builder clusterUrl(String clusterURL){
            this.clusterURL = clusterURL ;
            return  this ;
        }

        public Builder clusterName(final String clusterName){
            this.clusterName = clusterName ;
            return this ;
        }
        private void afterPropertiesSet() {
            //put the assertions
            assert this.connectionProtocol != null : "Connection Protocol can not be null";
            assert this.userId != null : "user id can not be null" ;
            assert this.password !=null : "user password can not be null" ;
            assert this.clusterURL !=null : "Cluster URL can not be null" ;
            assert this.clusterName != null : "Cluster name can not be null" ;
            assert this.databaseName !=null || this.databaseName =="" : "database name can not be null" ;
        }

        public ConnectionStringBuilder build() {
            afterPropertiesSet();
            ConnectionStringBuilder connectionStringBuilder = new ConnectionStringBuilder();
            connectionStringBuilder.connectionProtocol = this.connectionProtocol;
            connectionStringBuilder.connectionUrl = this.connectionUrl;
            connectionStringBuilder.userId = this.userId;
            connectionStringBuilder.password = this.password;
            connectionStringBuilder.databaseName = this.databaseName;
            connectionStringBuilder.clusterURL = this.clusterURL ;
            connectionStringBuilder.clusterName = this.clusterName ;
            return connectionStringBuilder;

        }
    }
}
