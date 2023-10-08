package com.ecommerce.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "category_name")
    private @NotBlank String categoryName;
    private @NotBlank String description;
    private @NotBlank String imageUrl;
}
