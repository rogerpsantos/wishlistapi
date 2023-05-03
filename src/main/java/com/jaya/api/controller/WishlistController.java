package com.jaya.api.controller;

import com.jaya.api.common.ApiResponse;
import com.jaya.api.model.Product;
import com.jaya.api.model.User;
import com.jaya.api.model.Wishlist;
import com.jaya.api.service.IProductService;
import com.jaya.api.service.IUserService;
import com.jaya.api.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

        if(isNotBlank(user.getName())){
            prod.add(this.productService.findById(product.getId()));
            wishlist = this.wishlistService.findWishListForUser(user);
            if(prod != null && wishlist != null){
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
        Wishlist wishlist = null;
        User user = this.userService.findById(user_id);
        if(isNotBlank(user.getName())){
            wishlist = this.wishlistService.findWishListForUser(user);
        }
        return ResponseEntity.ok(new ApiResponse<Wishlist>(wishlist));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Wishlist>> removeItemOfWishlist(@RequestParam String user_id, @RequestParam Product prod_id){
        Product prod;
        Wishlist wishlist;
        Wishlist finalWishlist;

        User user = this.userService.findById(user_id);

        if(isNotBlank(user.getName())) {
            prod = this.productService.findById(prod_id.getId());
            wishlist = this.wishlistService.findWishListForUser(user);

            if (prod != null && wishlist != null) {
                for(int i = wishlist.getProduct().size() - 1; i >= 0; i--){
                    if(wishlist.getProduct().get(i).getId().equals(prod.getId())){
                        wishlist.getProduct().remove(i);
                        finalWishlist = wishlist;
                        this.wishlistService.update(finalWishlist);
                        return ResponseEntity.ok(new ApiResponse<Wishlist>("Removed Item Of Wishlist", HttpStatus.OK));
                    }
                }
            }
        }

        return ResponseEntity.ok(new ApiResponse<Wishlist>("Not Remove Item Of Wishlist", HttpStatus.BAD_REQUEST));
    }
}



