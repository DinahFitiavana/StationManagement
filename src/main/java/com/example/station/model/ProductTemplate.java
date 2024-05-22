package com.example.station.model;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductTemplate {
    private int id;
    private String name;
    private double unitPrice;
}
