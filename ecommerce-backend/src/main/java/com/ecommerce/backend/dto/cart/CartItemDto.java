package com.ecommerce.backend.dto.cart;

import com.ecommerce.backend.model.Product;
import lombok.Data;

@Data
public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Product product;
}
