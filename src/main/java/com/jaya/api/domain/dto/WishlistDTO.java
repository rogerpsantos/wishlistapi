package com.jaya.api.domain.dto;

import com.jaya.api.domain.model.Product;
import com.jaya.api.domain.model.User;

import java.util.Date;
import java.util.List;

public record WishlistDTO(User user, List<Product> product, Date createDate) {
}
