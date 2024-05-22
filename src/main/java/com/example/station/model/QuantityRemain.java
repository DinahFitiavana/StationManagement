package com.example.station.model;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuantityRemain {
    private Timestamp date;
    private int idProduct;
    private int idStockMovement;
}
