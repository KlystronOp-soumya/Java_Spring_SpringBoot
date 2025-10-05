package com.demo.qrks.openapi;

import io.petstore.beans.Product;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProductService {

    public Optional<Product> getProductWithId(final String productId){
        return Optional.empty() ;
    }
}
