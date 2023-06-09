package com.jaya.api.repository;

import com.jaya.api.domain.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductRepository extends MongoRepository<Product, String> {

}