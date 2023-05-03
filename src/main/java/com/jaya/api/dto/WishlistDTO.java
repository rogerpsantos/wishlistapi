package com.jaya.api.dto;

import com.jaya.api.model.Product;
import com.jaya.api.model.User;

import java.util.Date;

public record WishlistDTO(User user, Product product, Date createDate) {
}
