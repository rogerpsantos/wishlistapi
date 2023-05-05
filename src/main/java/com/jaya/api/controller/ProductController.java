package com.jaya.api.controller;

import com.jaya.api.domain.dto.ProductDTO;
import com.jaya.api.domain.model.Product;
import com.jaya.api.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        return ResponseEntity.ok(this.productService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(this.productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody @Valid ProductDTO data, UriComponentsBuilder uriComponentsBuilder){
        var product = this.productService.add(data);
        var uri = uriComponentsBuilder.path("api/product/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
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
