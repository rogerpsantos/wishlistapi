package com.jaya.api.model;

import com.jaya.api.dto.WishlistDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {
    @Id
    private String id;
    private User user;
    private List<Product> product;
    private Date createDate;

    public Wishlist(User user, List<Product> product) {
        this.user = user;
        this.product = product;
        this.createDate = new Date();
    }
}
