package com.jaya.api.controller;

import com.jaya.api.domain.dto.ProductDTO;
import com.jaya.api.domain.model.Product;
import com.jaya.api.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listAll(){
        return ResponseEntity.ok(this.productService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(this.productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody @Valid ProductDTO data){
        return ResponseEntity.ok(this.productService.add(data));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Product> update(@PathVariable(name = "id") String id, @RequestBody @Valid  Product data) {
        data.setId(id);
        return ResponseEntity.ok(this.productService.update(data));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable(name = "id") String id){
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
