package com.jaya.api.service;

import com.jaya.api.domain.dto.ProductDTO;
import com.jaya.api.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<Product> listAll(Pageable pageable);
    Product findById(String id);
    Product add(ProductDTO product);
    Product update(Product product);
    void delete (String id);
}
