package com.jaya.api.model;

import com.jaya.api.dto.UserDTO;
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
}
