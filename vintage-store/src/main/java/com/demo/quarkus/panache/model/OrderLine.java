package com.demo.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "t_purchase_order_line")
public class OrderLine extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "item_fk")
    public Item item;

    @Column(nullable = false)
    public int quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_order_fk")
    public PurchaseOrder purchaseOrder;

    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();
}
