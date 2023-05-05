package com.jaya.api.repository;

import com.jaya.api.domain.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface IWishlistRepository extends MongoRepository<Wishlist, String> {

    @Query("{ 'user._id': ObjectId(?0) }")
    public Wishlist findWishListForUser(String id);

    @Query("{ 'user._id': ObjectId(?0)}, {'product._id': ObjectId(?1)} ")
    public Wishlist findProductInWishList(String user_id, String product_id);
}
