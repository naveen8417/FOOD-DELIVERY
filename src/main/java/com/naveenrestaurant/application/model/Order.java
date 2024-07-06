package com.naveenrestaurant.application.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;


    private long totalAmount;


    private String orderStatus;


    private Date createdAt;

    @ManyToOne
    private Address deliveryAddress;

   @OneToMany
    private List<OrderItem> items;

//    @ManyToOne
//    @JoinColumn(name = "payment_id")
//    private Payment payment;


    private int totalItem;


    private Long totalPrice;

    // Getters and setters
    // Constructors, equals, hashcode, and toString methods
}

