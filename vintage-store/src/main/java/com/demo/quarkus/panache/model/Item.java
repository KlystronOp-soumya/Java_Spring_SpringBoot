package com.demo.quarkus.panache.model;

import com.demo.quarkus.jpa.artist.Artist;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "t_items")
@Inheritance(strategy = InheritanceType.JOINED)
public class Item extends PanacheEntity {

    @Column(length = 100, nullable = false)
    public String title;
    @Column(length = 300)
    public String description;
    @Column(nullable = false)
    public BigDecimal price;
    @Column(name = "crated_date", nullable = false)
    public Instant createdDate = Instant.now();

    @ManyToOne
    @JoinColumn(name = "artist_fk")
    public Artist artist;

}
