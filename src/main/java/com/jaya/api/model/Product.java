package com.jaya.api.model;

import com.jaya.api.dto.Category;
import com.jaya.api.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;

    public Product(ProductDTO data) {
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.category = data.category();
    }
}