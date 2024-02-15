package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Integer> {
}
