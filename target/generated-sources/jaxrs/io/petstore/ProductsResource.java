package io.petstore;

import io.petstore.beans.NewProduct;
import io.petstore.beans.Product;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.math.BigInteger;
import org.eclipse.microprofile.openapi.annotations.Operation;

/**
 * A JAX-RS interface. An implementation of this interface must be provided.
 */
@Path("/products")
public interface ProductsResource {
  /**
   * <p>
   * Fetches a paginated list of products with optional filtering and sorting.
   * </p>
   * 
   */
  @Operation(description = "Fetches a paginated list of products with optional filtering and sorting.", summary = "Retrieve a list of products.")
  @GET
  @Produces("application/json")
  Response retrieveAListOfProducts(
      @QueryParam("limit") @DecimalMax(value = "100", inclusive = true) @DecimalMin(value = "1", inclusive = true) @DefaultValue("10") BigInteger limit,
      @QueryParam("offset") @DecimalMin(value = "0", inclusive = true) @DefaultValue("0") BigInteger offset,
      @QueryParam("categoryId") String categoryId, @QueryParam("q") String q);

  /**
   * <p>
   * Adds a new product to the catalog.
   * </p>
   * 
   */
  @Operation(description = "Adds a new product to the catalog.", summary = "Create a new product.")
  @POST
  @Produces("application/json")
  @Consumes("application/json")
  Product createANewProduct(@NotNull NewProduct data);

  /**
   * <p>
   * Fetches a single product by its unique ID.
   * </p>
   * 
   */
  @Operation(description = "Fetches a single product by its unique ID.", summary = "Retrieve a product by ID.")
  @Path("/{productId}")
  @GET
  @Produces("application/json")
  Product retrieveAProductByID(@PathParam("productId") String productId);

  /**
   * <p>
   * Updates all fields of a product by its ID.
   * </p>
   * 
   */
  @Operation(description = "Updates all fields of a product by its ID.", summary = "Update an existing product.")
  @Path("/{productId}")
  @PUT
  @Produces("application/json")
  @Consumes("application/json")
  Product updateAnExistingProduct(@PathParam("productId") String productId, @NotNull NewProduct data);

  /**
   * <p>
   * Deletes a product by its ID.
   * </p>
   * 
   */
  @Operation(description = "Deletes a product by its ID.", summary = "Delete a product.")
  @Path("/{productId}")
  @DELETE
  void deleteAProduct(@PathParam("productId") String productId);
}
