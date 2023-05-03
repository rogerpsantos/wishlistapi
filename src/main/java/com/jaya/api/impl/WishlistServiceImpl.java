package com.jaya.api.impl;

import com.jaya.api.dto.WishlistDTO;
import com.jaya.api.model.Product;
import com.jaya.api.model.User;
import com.jaya.api.model.Wishlist;
import com.jaya.api.repository.IWishlistRepository;
import com.jaya.api.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements IWishlistService {

    @Autowired
    private IWishlistRepository wishlistRepository;

    @Override
    public List<Wishlist> listAll() {
        return this.wishlistRepository.findAll();
    }

    @Override
    public Wishlist findByIdProduct(Product id) {
        return null;
    }

    @Override
    public Wishlist add(WishlistDTO wishlist) {
        return null;
    }

    @Override
    public Wishlist update(Wishlist wishlist) {
        return this.wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist delete(Wishlist wishlist) {
        return null;
    }

    @Override
    public void createWishlist(Wishlist wishlist) {
        this.wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist findWishListForUser(User wishlist) {
        return this.wishlistRepository.findWishListForUser(wishlist.getName());
    }
}
