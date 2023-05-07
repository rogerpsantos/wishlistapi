package com.jaya.api.service;

import com.jaya.api.domain.model.User;
import com.jaya.api.domain.model.Wishlist;

public interface IWishlistService {

    Wishlist update(Wishlist id);
    Wishlist createWishlist(Wishlist wishlist);
    Wishlist findWishListForUser(User user);

    Wishlist findProductInWishList(String user_id, String product_id);
}
