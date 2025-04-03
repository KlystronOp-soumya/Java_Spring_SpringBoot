package com.demo.utils;

public enum PropKeys {
    DB_CONFIG_PROP("db-config.properties"),
    MONGO_ATLAS_URL_PROTOCOL("mongo_db.atlas.url_protocol"),
    MONGO_ATLAS_CREDS("mongo_db.atlas.creds"),
    MONGO_ATLAS_USERID("mongo.atlas.userid"),
    MONGO_ATLAS_PASSWORD("mongo.atlas.password"),
    MONGO_ATLAS_CLUSTER("mongo_db.atlas.cluster_name"),
    MONGO_ATLAS_CLUSTER_URL("mongo_db.atlas.cluster_url"),
    MONGO_ATLAS_DB_NAME("mongo_db.atlas.db_name");

    String value;

    PropKeys(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
