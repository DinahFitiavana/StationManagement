package com.example.station.model;


import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private int idStation;
    private int idProduct;
    private int idStockMovement;
    private int idEvaporationRate;
}
