package com.jaya.api.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record UserDTO(
        @NotBlank(message = "Name cannot be null")
        String name,
        @NotBlank(message = "Email cannot be null")
        @Email(message = "Email is not valid")
        String email,
        String telephone,
        @NotBlank(message = "CPF cannot be null")
        @CPF(message = "CPF is not valid")
        String cpf,
        @NotNull(message = "Address cannot be null")
        @Valid
        AddressDTO address
) { }
