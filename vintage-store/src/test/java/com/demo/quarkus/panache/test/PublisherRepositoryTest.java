package com.demo.quarkus.panache.test;

import com.demo.quarkus.panache.model.Publisher;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PublisherRepositoryTest {

    @Test
    @TestTransaction
    public void testSavePublisherAndFindById() {
        Publisher publisher = new Publisher("Penguine");
        Publisher.persist(publisher);

        Publisher publisherAct = Publisher.findById(publisher.id);

        Assertions.assertNotNull(publisherAct);
        Assertions.assertEquals(publisher.id, publisherAct.id);
    }
}
