package com.jaya.api.controller;

import com.jaya.api.common.ApiResponse;
import com.jaya.api.dto.WishlistDTO;
import com.jaya.api.model.Product;
import com.jaya.api.model.User;
import com.jaya.api.model.Wishlist;
import com.jaya.api.repository.IUserRepository;
import com.jaya.api.service.IProductService;
import com.jaya.api.service.IUserService;
import com.jaya.api.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {

    @Autowired
    private IWishlistService wishlistService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Wishlist>> addToWishlist(@RequestBody Product product,
                                                               @RequestParam String user_id) {
        List<Product> prod = new ArrayList<>();
        Wishlist wishlist;

        User user = this.userService.findById(user_id);
        prod.add(this.productService.findById(product.getId()));

        if(isNotBlank(user.getName())){
            wishlist = this.wishlistService.findWishListForUser(user);
            if(wishlist != null){
                wishlist.getProduct().add(this.productService.findById(product.getId()));
                this.wishlistService.update(wishlist);
                return ResponseEntity.ok(new ApiResponse<Wishlist>("Added To Wishlist", HttpStatus.CREATED));
            }

            wishlist = new Wishlist(user, prod);
            this.wishlistService.createWishlist(wishlist);
            return ResponseEntity.ok(new ApiResponse<Wishlist>("Created and Added To Wishlist", HttpStatus.CREATED));
        }
        return ResponseEntity.ok(new ApiResponse<Wishlist>("Not Add To Wishlist", HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Wishlist>> allProductsInWishlist(@RequestParam String user_id){
        User user = this.userService.findById(user_id);
        return ResponseEntity.ok(new ApiResponse<Wishlist>(this.wishlistService.findWishListForUser(user)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Wishlist>> removeItemOfWishlist(@PathVariable(name = "id") Product prod_id, @RequestParam String user_id){
        List<Product> prod = new ArrayList<>();
        Wishlist wishlist;

        User user = this.userService.findById(user_id);
        prod.remove(this.productService.findById(prod_id.getId()));
        return ResponseEntity.ok(new ApiResponse<Wishlist>(this.wishlistService.findWishListForUser(user)));

    }
}
