package com.jaya.api.service;

import com.jaya.api.domain.dto.ProductDTO;
import com.jaya.api.domain.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> listAll();
    Product findById(String id);
    Product add(ProductDTO product);
    Product update(Product product);
    void delete (String id);
}
