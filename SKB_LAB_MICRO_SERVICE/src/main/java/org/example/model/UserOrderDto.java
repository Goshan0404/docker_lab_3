package org.example.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserOrderDto {
    private long userId;
    private Set<PizzaDto> pizzas = new HashSet<>();

}