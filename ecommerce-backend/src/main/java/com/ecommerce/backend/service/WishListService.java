package com.ecommerce.backend.service;

import com.ecommerce.backend.model.WishList;
import com.ecommerce.backend.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final WishListRepository wishListRepository;

    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }
}
