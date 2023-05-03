package com.jaya.api.model;

import com.jaya.api.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address{


    private String street;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String state;
    private String complement;
    private String number;

    public Address(AddressDTO data) {
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.zipcode = data.zipcode();
        this.city = data.city();
        this.state = data.state();
        this.complement = data.complement();
        this.number = data.number();
    }
}