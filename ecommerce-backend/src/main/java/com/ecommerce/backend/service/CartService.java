package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.cart.AddToCartDto;
import com.ecommerce.backend.dto.cart.CartDto;
import com.ecommerce.backend.dto.cart.CartItemDto;
import com.ecommerce.backend.exceptions.CustomException;
import com.ecommerce.backend.model.Cart;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    private final ProductService productService;

    public void addToCart(AddToCartDto addToCartDto, User user) {
        Product product = productService.findById(addToCartDto.getProductId());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());

        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = new CartItemDto();
            BeanUtils.copyProperties(cart, cartItemDto);
            totalCost += cartItemDto.getQuantity() * cartItemDto.getProduct().getPrice();
            cartItems.add(cartItemDto);
        }
        CartDto cartDto = new CartDto();
        cartDto.setCartItems(cartItems);
        cartDto.setTotalCost(totalCost);
        return cartDto;
    }

    public void deleteCartItem(Integer itemId, User user) {
        Cart cart = cartRepository.findById(itemId)
                .orElseThrow(() -> new CustomException("cart item id " + itemId + " is invalid"));
        if (cart.getUser() != user) {
            throw new CustomException("cart item " + itemId + " does not belong to user");
        }
        cartRepository.delete(cart);
    }
}
