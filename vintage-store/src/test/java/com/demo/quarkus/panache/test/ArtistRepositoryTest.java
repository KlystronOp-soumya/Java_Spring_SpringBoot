package com.demo.quarkus.panache.test;

import com.demo.quarkus.jpa.artist.Artist;
import com.demo.quarkus.panache.repos.ArtistRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class ArtistRepositoryTest {

    private ArtistRepository artistRepository;

    public ArtistRepositoryTest(ArtistRepository artistRepository) {
        super();
        this.artistRepository = artistRepository;
    }

    @Test
    @TestTransaction
    public void shouldCreateAndFindArtist() {
        Artist artist = new Artist("name", "bio");

        artistRepository.persist(artist);

        artist = artistRepository.findById(artist.getId());

        assertEquals("name", artist.getName());
    }

}
