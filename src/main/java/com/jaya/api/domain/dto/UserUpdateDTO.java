package com.jaya.api.domain.dto;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(
        @NotNull
        String id,
        String name,
        String email,
        String telephone,
        String cpf,
        AddressUpdateDTO address

) {
}
