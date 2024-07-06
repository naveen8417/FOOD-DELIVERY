package com.naveenrestaurant.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class ContactInformation {



    private  String email;
    private  String twitter;
    private  String instagram;
    private  String phoneno;
}
