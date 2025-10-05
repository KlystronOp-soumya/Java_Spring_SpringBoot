package com.demo.qrks.openapi;

import io.petstore.ProductsResource;
import io.petstore.beans.NewProduct;
import io.petstore.beans.Product;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.math.BigInteger;

public class ProductResourceImpl implements ProductsResource {

    @Inject
    private transient ProductService productService ;

    @Override
    public Response retrieveAListOfProducts(BigInteger limit, BigInteger offset, String categoryId, String q) {
        return null;
    }

    @Override
    public Product createANewProduct(NewProduct data) {
        return null;
    }

    @Override
    public Product retrieveAProductByID(String productId) {
        return this.productService.getProductWithId(productId).orElse(new Product());
    }

    @Override
    public Product updateAnExistingProduct(String productId, NewProduct data) {
        return null;
    }

    @Override
    public void deleteAProduct(String productId) {

    }
}