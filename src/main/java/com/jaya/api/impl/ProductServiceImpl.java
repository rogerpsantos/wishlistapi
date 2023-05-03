package com.jaya.api.impl;

import com.jaya.api.dto.ProductDTO;
import com.jaya.api.model.Product;
import com.jaya.api.repository.IProductRepository;
import com.jaya.api.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productService;
    @Override
    public List<Product> listAll() {
        return this.productService.findAll();
    }

    @Override
    public Product findById(String id) {
        return this.productService.findById(id).orElseThrow(()-> new IllegalArgumentException("Product does not exist"));
    }

    @Override
    public Product add(ProductDTO data) {
        return this.productService.save(new Product(data));
    }

    @Override
    public Product update(Product data) {
        return this.productService.save(data);
    }

    @Override
    public void delete(String id) {
        this.productService.deleteById(id);
    }
}
