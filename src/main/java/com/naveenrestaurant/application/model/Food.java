package com.naveenrestaurant.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     private  String name;
     private  String description;
    private  long price;

    @ManyToOne
    private  Category category;


    @ElementCollection
    @Column(length = 1000)
    private List<String> images;

    private boolean available;

    @ManyToOne
    private Restaurant restaurant;
    private  boolean isvegetarian;
    private boolean isnonvegetarian;
    private boolean isseasonal;

    @ManyToMany
    private List<IngrediantsList> ingrediants = new ArrayList<>();

    private LocalDateTime creationDate;




}
