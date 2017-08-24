package com.jennilyn.models;

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

    //OrderProduct has MANY products
    @OneToMany(mappedBy = "order_product", cascade = CascadeType.PERSIST)
    private List<Product> products;

    public OrderProduct() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        int quantity = 0;
        for (Product product : products){
            quantity ++;
        }
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getCustomerOrder() {
        return customer_order;
    }

    public void setCustomerOrder(Order customerOrder) {
        this.customer_order = customerOrder;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
