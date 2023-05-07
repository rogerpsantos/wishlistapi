package com.jaya.api.controller;

import com.jaya.api.domain.model.Product;
import com.jaya.api.domain.model.User;
import com.jaya.api.domain.model.Wishlist;
import com.jaya.api.exceptions.GlobalRuntimeException;
import com.jaya.api.service.IProductService;
import com.jaya.api.service.IUserService;
import com.jaya.api.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    private static final int TOTAL_SIZE = 20;

    @PostMapping
    public ResponseEntity<Wishlist> addToWishlist(@RequestBody Product prod_id, @RequestParam String user_id, UriComponentsBuilder uriComponentsBuilder) {
        List<Product> prod = new ArrayList<>();
        Wishlist wishlist = null;

        User user = this.userService.findById(user_id);

        if(isNotBlank(user.getName())){
            prod.add(this.productService.findById(prod_id.getId()));
            wishlist = this.wishlistService.findWishListForUser(user);
            if(prod != null && wishlist != null){
                if(wishlist.getProduct().size() < TOTAL_SIZE){
                    wishlist.getProduct().add(this.productService.findById(prod_id.getId()));
                    this.wishlistService.update(wishlist);
                    var uri = uriComponentsBuilder.path("api/wishlist/{id}").buildAndExpand(wishlist.getId()).toUri();

                    return ResponseEntity.created(uri).body(wishlist);
                } else {
                    throw new GlobalRuntimeException("The wish list has 20 products to add a new product you must delete at least one product from the list.");
                }
            }
            wishlist = new Wishlist(user, prod);
            this.wishlistService.createWishlist(wishlist);
            var uri = uriComponentsBuilder.path("api/wishlist/{id}").buildAndExpand(wishlist.getId()).toUri();
            return ResponseEntity.created(uri).body(wishlist);
        }
        throw new GlobalRuntimeException("User not found.");
    }

    @GetMapping
    public ResponseEntity<Wishlist> allProductsInWishlist(@RequestParam String user_id){
        Wishlist wishlist = null;
        User user = this.userService.findById(user_id);
        if(isNotBlank(user.getName())){
            wishlist = this.wishlistService.findWishListForUser(user);
            return ResponseEntity.ok(wishlist);
        }
        throw new GlobalRuntimeException("User does not have a wishlist.");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> itemOfWishlist(@RequestParam String user_id, @PathVariable(name = "id") String prod_id){
        Product prod;
        Wishlist wishlist;
        Wishlist isExist;
        User user = this.userService.findById(user_id);

        if(isNotBlank(user.getName())) {
            prod = this.productService.findById(prod_id);
            wishlist = this.wishlistService.findWishListForUser(user);

            if (prod != null && wishlist != null) {
                for(int i = wishlist.getProduct().size() - 1; i >= 0; i--){
                    if(wishlist.getProduct().get(i).getId().equals(prod.getId())){
                        isExist = this.wishlistService.findProductInWishList(user.getId().toString(), prod.getId().toString());
                        if (isExist == null){
                            throw new GlobalRuntimeException("Product not found in wishlist");
                        } else return ResponseEntity.ok().body("The product is already in the wishlist");
                    }
                }
            }
        }
        throw new GlobalRuntimeException("User does not have a wishlist.");
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Wishlist> removeItemOfWishlist(@RequestParam String user_id, @PathVariable(name = "id") String prod_id){
        Product prod;
        Wishlist wishlist;
        Wishlist finalWishlist;

        User user = this.userService.findById(user_id);

        if(isNotBlank(user.getName())) {
            prod = this.productService.findById(prod_id);
            wishlist = this.wishlistService.findWishListForUser(user);

            if (prod != null && wishlist != null) {
                for(int i = wishlist.getProduct().size() - 1; i >= 0; i--){
                    if(wishlist.getProduct().get(i).getId().equals(prod.getId())){
                        wishlist.getProduct().remove(i);
                        finalWishlist = wishlist;
                        return ResponseEntity.ok(this.wishlistService.update(finalWishlist));
                    }
                }
            }
            throw new GlobalRuntimeException("Not Remove Item Of Wishlist");
        }
        throw new GlobalRuntimeException("User does not have a wishlist.");
    }
}



