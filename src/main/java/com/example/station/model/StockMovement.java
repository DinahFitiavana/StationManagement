package com.example.station.model;


import lombok.*;

import java.sql.Timestamp;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockMovement {
    private int id;
    private Timestamp date;
    private String type;
    private double quantity;
}
