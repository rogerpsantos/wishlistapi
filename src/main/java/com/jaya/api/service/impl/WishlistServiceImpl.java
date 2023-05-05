package com.jaya.api.service.impl;

import com.jaya.api.domain.model.User;
import com.jaya.api.domain.model.Wishlist;
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
    public Wishlist update(Wishlist wishlist) {
        return this.wishlistRepository.save(wishlist);
    }

    @Override
    public void createWishlist(Wishlist wishlist) {
        this.wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist findWishListForUser(User wishlist) {
        return this.wishlistRepository.findWishListForUser(wishlist.getId());
    }

    @Override
    public void delete(String id) {
        this.wishlistRepository.deleteById(id);
    }

    @Override
    public boolean findProductInWishList(String user_id, String product_id) {
        boolean isExist = false;
        if(this.wishlistRepository.findProductInWishList(user_id, product_id) != null) {
            isExist = true;
        } else isExist = false;
        return isExist;
    }
}
