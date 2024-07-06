package com.naveenrestaurant.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User owner;

    private String name;

    private String cuisineType;
    private String description;

    @OneToOne
    private Address address;

    @Embedded
    private  ContactInformation contactinformation;

    private String openingHours;



    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Order> order = new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    private List<String> images;

    private boolean open;

    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private  List<Food> foods = new ArrayList<>();


}
