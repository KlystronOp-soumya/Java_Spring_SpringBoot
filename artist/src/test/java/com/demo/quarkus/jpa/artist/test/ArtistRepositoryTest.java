package com.demo.quarkus.jpa.artist.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.quarkus.jpa.artist.Artist;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ArtistRepositoryTest {

    private ArtistRepository artistRepository;

    public ArtistRepositoryTest(ArtistRepository artistRepository) {
        super();
        this.artistRepository = artistRepository;
    }

    @Test
    public void shouldCreateAndFindArtist() {

        Artist artist = new Artist("name", "bio");

        artistRepository.persist(artist);

        artist = artistRepository.findById(artist.getId());

        assertEquals("name", artist.getName());
    }

}
