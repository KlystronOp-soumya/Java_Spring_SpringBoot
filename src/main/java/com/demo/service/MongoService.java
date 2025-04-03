package com.demo.service;

import com.demo.dao.MongoDAO;

public class MongoService {

    private transient MongoDAO mongoDAO ;

    public  MongoService(final MongoDAO mongoDAO)
    {
        this.mongoDAO=mongoDAO ;
    }

}
