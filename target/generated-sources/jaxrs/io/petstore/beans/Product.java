
package io.petstore.beans;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "productId",
    "name",
    "description",
    "sku",
    "price",
    "currency",
    "stock",
    "categoryId",
    "images",
    "createdAt",
    "updatedAt"
})
@Generated("jsonschema2pojo")
public class Product {

    /**
     * The unique identifier for the product.
     * (Required)
     * 
     */
    @JsonProperty("productId")
    @JsonPropertyDescription("The unique identifier for the product.")
    private String productId;
    /**
     * The name of the product.
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The name of the product.")
    private String name;
    /**
     * A detailed description of the product.
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("A detailed description of the product.")
    private String description;
    /**
     * Stock Keeping Unit. A unique identifier for the product variant.
     * (Required)
     * 
     */
    @JsonProperty("sku")
    @JsonPropertyDescription("Stock Keeping Unit. A unique identifier for the product variant.")
    private String sku;
    /**
     * The current price of the product.
     * (Required)
     * 
     */
    @JsonProperty("price")
    @JsonPropertyDescription("The current price of the product.")
    private Double price;
    /**
     * The currency of the price.
     * 
     */
    @JsonProperty("currency")
    @JsonPropertyDescription("The currency of the price.")
    private String currency = "USD";
    /**
     * The number of units in stock.
     * 
     */
    @JsonProperty("stock")
    @JsonPropertyDescription("The number of units in stock.")
    private Integer stock;
    /**
     * The category the product belongs to.
     * 
     */
    @JsonProperty("categoryId")
    @JsonPropertyDescription("The category the product belongs to.")
    private String categoryId;
    /**
     * A list of image URLs for the product.
     * 
     */
    @JsonProperty("images")
    @JsonPropertyDescription("A list of image URLs for the product.")
    private List<URI> images = new ArrayList<URI>();
    /**
     * The timestamp when the product was created.
     * 
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @JsonProperty("createdAt")
    @JsonPropertyDescription("The timestamp when the product was created.")
    private Date createdAt;
    /**
     * The timestamp when the product was last updated.
     * 
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @JsonProperty("updatedAt")
    @JsonPropertyDescription("The timestamp when the product was last updated.")
    private Date updatedAt;

    /**
     * The unique identifier for the product.
     * (Required)
     * 
     */
    @JsonProperty("productId")
    public String getProductId() {
        return productId;
    }

    /**
     * The unique identifier for the product.
     * (Required)
     * 
     */
    @JsonProperty("productId")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * The name of the product.
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * The name of the product.
     * (Required)
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A detailed description of the product.
     * 
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * A detailed description of the product.
     * 
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Stock Keeping Unit. A unique identifier for the product variant.
     * (Required)
     * 
     */
    @JsonProperty("sku")
    public String getSku() {
        return sku;
    }

    /**
     * Stock Keeping Unit. A unique identifier for the product variant.
     * (Required)
     * 
     */
    @JsonProperty("sku")
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * The current price of the product.
     * (Required)
     * 
     */
    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    /**
     * The current price of the product.
     * (Required)
     * 
     */
    @JsonProperty("price")
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * The currency of the price.
     * 
     */
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    /**
     * The currency of the price.
     * 
     */
    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * The number of units in stock.
     * 
     */
    @JsonProperty("stock")
    public Integer getStock() {
        return stock;
    }

    /**
     * The number of units in stock.
     * 
     */
    @JsonProperty("stock")
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * The category the product belongs to.
     * 
     */
    @JsonProperty("categoryId")
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * The category the product belongs to.
     * 
     */
    @JsonProperty("categoryId")
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * A list of image URLs for the product.
     * 
     */
    @JsonProperty("images")
    public List<URI> getImages() {
        return images;
    }

    /**
     * A list of image URLs for the product.
     * 
     */
    @JsonProperty("images")
    public void setImages(List<URI> images) {
        this.images = images;
    }

    /**
     * The timestamp when the product was created.
     * 
     */
    @JsonProperty("createdAt")
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * The timestamp when the product was created.
     * 
     */
    @JsonProperty("createdAt")
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * The timestamp when the product was last updated.
     * 
     */
    @JsonProperty("updatedAt")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * The timestamp when the product was last updated.
     * 
     */
    @JsonProperty("updatedAt")
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
