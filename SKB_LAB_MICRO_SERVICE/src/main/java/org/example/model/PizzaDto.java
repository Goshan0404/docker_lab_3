package org.example.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class PizzaDto {
    private String name;
    private int size;
    private Set<IngredientDto> ingredients = new HashSet<>();
}