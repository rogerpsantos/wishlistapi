package com.jaya.api.domain.model;

import com.jaya.api.domain.dto.UserDTO;
import com.jaya.api.domain.dto.UserUpdateDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String name;

    private String email;
    private String telephone;

    private String cpf;
    private Address address;

    public User(UserDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.telephone = data.telephone();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
    }

    public void userUpdate(UserUpdateDTO data){
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.email() != null){
            this.email = data.email();
        }
        if(data.telephone() != null){
            this.telephone = data.telephone();
        }
        if(data.cpf() != null){
            this.cpf = data.cpf();
        }
        if(data.address() != null){
            this.address.AddressUpdate(data.address());
        }
    }
}
