package com.jennilyn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_quantity")
    private int quantity;

    //OrderProduct has ONE customerOrder
    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private Order customer_order;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    public OrderProduct() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getCustomer_order() {
        return customer_order;
    }

    public void setCustomer_order(Order customer_order) {
        this.customer_order = customer_order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
