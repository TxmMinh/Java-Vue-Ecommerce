package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.product.ProductDto;
import com.ecommerce.backend.exceptions.ProductNotExistsException;
import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setCategory(category);
        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : allProducts) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product, productDto);
            productDto.setCategoryId(product.getCategory().getId());
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Category category, Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("product does not exist"));
        BeanUtils.copyProperties(productDto, product);
        product.setCategory(category);
        product.setId(productId);
        productRepository.save(product);
    }

    public Product findById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotExistsException("Product id " + productId + " is invalid"));
    }
}
