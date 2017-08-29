package com.jennilyn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jennilyn.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class Order {

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

    @Column(name = "service_requested_at")
    private Timestamp serviceRequestedAt;

    private String location;
    private String notes;
    private boolean fulfilled;

    //Order has ONE user but user can have many orders
    @ManyToOne
    @JoinColumn(name = "rental_user_id", insertable = true)
    @JsonManagedReference
    private User rentaluser;

    //Order has MANY orderProducts
    @OneToMany(mappedBy = "customer_order", cascade = CascadeType.PERSIST)
    @JsonManagedReference
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

    public User getRentaluser() {
        return rentaluser;
    }

    public void setRentaluser(User rentaluser) {
        this.rentaluser = rentaluser;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getServiceRequestedAt() {
        return serviceRequestedAt;
    }

    public void setServiceRequestedAt(Timestamp serviceRequestedAt) {
        this.serviceRequestedAt = serviceRequestedAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    //    public double calculateTotal(){
//        double calculatedTotal = 0.0;
//        for (OrderProduct orderProduct : orderProducts){
//            int quantity = orderProduct.getQuantity();
//            List<Product> products = orderProduct.getProducts();
//            for (Product product : products){
//                double subtotal = product.getSalePrice();
//                calculatedTotal += quantity * subtotal;
//            }
//        }
//        return calculatedTotal;
//    }
}
