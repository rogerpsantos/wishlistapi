package com.jaya.api.controller;

import com.jaya.api.common.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<Product>>> listAll(){
        return ResponseEntity.ok(new ApiResponse<List<Product>>(this.productService.listAll()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Product>> findById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(new ApiResponse<Product>(this.productService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> add(@RequestBody @Valid ProductDTO data, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = new ArrayList<String>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new ApiResponse<Product>(errors));
        }
        return ResponseEntity.ok(new ApiResponse<Product>(this.productService.add(data)));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Product>> update(@PathVariable(name = "id") String id, @RequestBody @Valid  Product data, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = new ArrayList<String>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new ApiResponse<Product>(errors));
        }

        data.setId(id);
        return ResponseEntity.ok(new ApiResponse<Product>(this.productService.update(data)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable(name = "id") String id){
        this.productService.delete(id);
        return ResponseEntity.ok(new ApiResponse<Integer>(1));
    }

}
