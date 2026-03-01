package com.demo.quarkus.panache.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Book extends Item {

    @Column(length = 15, nullable = false)
    public String isbn;
    @Column(name = "nb_of_pages")
    public int nbOfPages;
    @Column(name = "publication_date")
    public LocalDate publicationDate;
    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    public Language language;

    @ManyToOne
    @JoinColumn(name = "publisher_fk")
    public Publisher publisher;

}
