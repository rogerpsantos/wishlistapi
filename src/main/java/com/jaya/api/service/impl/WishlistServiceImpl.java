package com.jaya.api.service.impl;

import com.jaya.api.domain.model.User;
import com.jaya.api.domain.model.Wishlist;
import com.jaya.api.repository.IWishlistRepository;
import com.jaya.api.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements IWishlistService {

    @Autowired
    private IWishlistRepository wishlistRepository;

    @Override
    public Wishlist update(Wishlist wishlist) {
        return this.wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        return this.wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist findWishListForUser(User wishlist) {
        return this.wishlistRepository.findWishListForUser(wishlist.getId());
    }

    @Override
    public Wishlist findProductInWishList(String user_id, String product_id) {
        var wishlist = this.wishlistRepository.findProductInWishList(user_id, product_id);
        return wishlist;
    }
}
