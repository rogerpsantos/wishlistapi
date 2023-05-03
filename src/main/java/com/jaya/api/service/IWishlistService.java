package com.jaya.api.service;

import com.jaya.api.model.User;
import com.jaya.api.model.Wishlist;
import java.util.List;

public interface IWishlistService {

    List<Wishlist> listAll();
    Wishlist update(Wishlist id);
    void createWishlist(Wishlist wishlist);
    Wishlist findWishListForUser(User user);
    void delete (String id);

}
