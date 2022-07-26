package com.andrea.pampas.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class Color {

    private int id;

    @NotBlank(message = "The color field should not be blank.")
    private String color;

}
