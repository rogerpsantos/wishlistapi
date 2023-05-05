package com.jaya.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressUpdateDTO(
        @NotBlank
        String street,
        @NotBlank
        String neighborhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String zipcode,
        @NotBlank
        String city,
        @NotBlank
        String state,
        String complement,
        @NotBlank
        String number
) {
}
