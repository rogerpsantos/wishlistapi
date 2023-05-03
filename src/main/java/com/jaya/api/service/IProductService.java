package com.jaya.api.service;

import com.jaya.api.dto.ProductDTO;
import com.jaya.api.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> listAll();
    Product findById(String id);
    Product add(ProductDTO product);
    Product update(Product product);
    void delete (String id);
}
