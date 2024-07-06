package com.naveenrestaurant.application.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="IngrediantsList")
public class IngrediantsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;

    @ManyToOne
    private Ingrediantscategory category;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    private boolean instock =true;

}
