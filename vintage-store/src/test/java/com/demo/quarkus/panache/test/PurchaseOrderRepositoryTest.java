package com.demo.quarkus.panache.test;

import com.demo.quarkus.jpa.artist.Artist;
import com.demo.quarkus.jpa.customer.Customer;
import com.demo.quarkus.panache.model.Book;
import com.demo.quarkus.panache.model.Language;
import com.demo.quarkus.panache.model.OrderLine;
import com.demo.quarkus.panache.model.Publisher;
import com.demo.quarkus.panache.model.PurchaseOrder;
import com.demo.quarkus.panache.repos.CustomerRepo;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@QuarkusTest
class PurchaseOrderRepositoryTest {

    //@Inject
    private CustomerRepo customerRepo;

    PurchaseOrderRepositoryTest(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Test
    @TestTransaction
    void testCreateAndFindPurchaseOrder() {

        Artist artist = new Artist("John Doe", "artist bio");
        Publisher  publisher = new Publisher("Penguine");

        Book book = new Book();
        book.title = "Book Title";
        book.isbn = "ISBN";
        book.price = BigDecimal.valueOf(10);
        book.nbOfPages = 500;
        book.language = Language.ENGLISH;

        book.publisher = publisher;
        book.artist = artist;

        Book.persist(book);

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@email.com");

        customerRepo.persist(customer);

        OrderLine orderLine = new OrderLine();
        orderLine.item = book;
        orderLine.quantity = 2;

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.addOrderLine(orderLine);

        purchaseOrder.persist();


    }

}
