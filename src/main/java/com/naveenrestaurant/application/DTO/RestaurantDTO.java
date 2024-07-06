package com.naveenrestaurant.application.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.Length;

import java.util.List;
import java.util.Locale;

@Data
@Embeddable
public class RestaurantDTO {

    private String title;

    @Column(length=1000)
    private List<String> images;

    private  String description;
    private Long id;


}
