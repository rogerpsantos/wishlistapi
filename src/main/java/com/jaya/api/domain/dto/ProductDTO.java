package com.jaya.api.domain.dto;

import com.jaya.api.domain.enums.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;

public record ProductDTO(
        @NotBlank
        @UniqueElements
        String name,
        @NotBlank
        String description,
        @NotNull
        BigDecimal price,
        @NotNull
        @Valid
        Category category
) { }
