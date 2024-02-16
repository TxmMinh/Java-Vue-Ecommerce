package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.product.ProductDto;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.model.WishList;
import com.ecommerce.backend.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final WishListRepository wishListRepository;

    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishListForUser(User user) {
        List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList wishList : wishLists) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(wishList.getProduct(), productDto);
            productDto.setCategoryId(wishList.getProduct().getCategory().getId());
            productDtos.add(productDto);
        }
        return productDtos;
    }
}
