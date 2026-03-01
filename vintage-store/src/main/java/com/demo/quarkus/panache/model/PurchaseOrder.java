package com.demo.quarkus.panache.model;

import com.demo.quarkus.jpa.customer.Customer;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_purchase_orders")
public class PurchaseOrder extends PanacheEntity {

    @Column(name = "purchase_order_date", nullable = false)
    public LocalDate date = LocalDate.now();
    @OneToMany(mappedBy = "purchaseOrder")
    public List<OrderLine> orderLines = new ArrayList<OrderLine>();
    @ManyToOne
    @JoinColumn(name = "customer_fk")
    public Customer customer;
    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
        orderLine.purchaseOrder = this;
    }
}
