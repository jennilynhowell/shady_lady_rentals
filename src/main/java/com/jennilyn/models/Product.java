package com.jennilyn.models;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String productName;
    private double purchaseCost;
    private double salePrice;
    private int numberOnHand;
    
}
