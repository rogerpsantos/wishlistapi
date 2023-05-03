package com.jaya.api.service;

import com.jaya.api.dto.ProductDTO;
import com.jaya.api.dto.WishlistDTO;
import com.jaya.api.model.Product;
import com.jaya.api.model.User;
import com.jaya.api.model.Wishlist;

import java.util.List;

public interface IWishlistService {

    List<Wishlist> listAll();
    Wishlist findByIdProduct(Product id);
    Wishlist add(WishlistDTO wishlist);
    Wishlist update(Wishlist id);
    Wishlist delete(Wishlist wishlist);
    void createWishlist(Wishlist wishlist);

    Wishlist findWishListForUser(User user);

}
