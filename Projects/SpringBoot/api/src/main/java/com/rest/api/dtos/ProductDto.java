package com.rest.api.dtos;

public record ProductDto(
    String name,
    Double price,
    String description,
    long quantity
) {
 
}
