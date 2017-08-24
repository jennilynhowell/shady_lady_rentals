package com.jennilyn.models;

import com.jennilyn.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class Order {

    @Autowired
    ProductRepository productRepo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at", insertable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "processed_at")
    private Timestamp processedAt;

    @Column(name = "service_completed_at")
    private Timestamp serviceCompletedAt;

    //Order has ONE user but user can have many orders
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User rental_user;

    //Order has MANY orderProducts
    @OneToMany(mappedBy = "customer_order", cascade = CascadeType.PERSIST)
    private List<OrderProduct> orderProducts;

    //TODO: calculate total
    private double total;

    public Order() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public User getRental_user() {
        return rental_user;
    }

    public void setRental_user(User rental_user) {
        this.rental_user = rental_user;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Timestamp processedAt) {
        this.processedAt = processedAt;
    }

    public Timestamp getServiceCompletedAt() {
        return serviceCompletedAt;
    }

    public void setServiceCompletedAt(Timestamp serviceCompletedAt) {
        this.serviceCompletedAt = serviceCompletedAt;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    //order has a list of OrderProducts, which has a list of Products that have .getSalePrice()
    public double getTotal() {
        double total = 0.0;
        for (OrderProduct orderProduct : orderProducts){
            int quantity = orderProduct.getQuantity();
            List<Product> products = orderProduct.getProducts();
            for (Product product : products){
                double subtotal = product.getSalePrice();
                total += quantity * subtotal;
            }
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}