package com.jaya.api.repository;

import com.jaya.api.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface IWishlistRepository extends MongoRepository<Wishlist, String> {

    @Query("{ 'user.name': ?0}")
    public Wishlist findWishListForUser(String name);
}
