package com.demo.quarkus.panache.repos;

import com.demo.quarkus.jpa.artist.Artist;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;

@Singleton
public class ArtistRepository implements PanacheRepository<Artist> {
}
