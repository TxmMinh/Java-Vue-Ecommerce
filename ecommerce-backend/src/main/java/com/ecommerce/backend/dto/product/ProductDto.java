package com.ecommerce.backend.dto.product;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    private Integer id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;
}
