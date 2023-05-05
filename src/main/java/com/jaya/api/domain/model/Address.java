package com.jaya.api.domain.model;

import com.jaya.api.domain.dto.AddressDTO;
import com.jaya.api.domain.dto.AddressUpdateDTO;
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

    public void AddressUpdate(AddressUpdateDTO data) {
        if(data.street() != null){
            this.street = data.street();    
        }
        if(data.neighborhood() != null){
            this.neighborhood = data.neighborhood();
        }
        if(data.zipcode() != null){
            this.zipcode = data.zipcode();
        }
        if(data.city() != null){
            this.city = data.city();
        }
        if(data.state() != null){
            this.state = data.state();
        }
        if(data.complement() != null){
            this.complement = data.complement();
        }
        if(data.number() != null){
            this.number = data.number();
        }
    }
}