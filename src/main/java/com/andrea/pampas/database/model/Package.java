package com.andrea.pampas.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Package {

    private int id;
    private String name;
    private String description;
    private int stems;
    private int colorId;
    private int sizeId;
    private int typeId;
    private int quantity;
    private double price;
}
