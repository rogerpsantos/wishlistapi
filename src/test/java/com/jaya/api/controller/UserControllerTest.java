package com.jaya.api.controller;

import com.jaya.api.domain.dto.AddressDTO;
import com.jaya.api.domain.dto.UserDTO;
import com.jaya.api.domain.dto.UserUpdateDTO;
import com.jaya.api.domain.enums.Category;
import com.jaya.api.domain.model.Address;
import com.jaya.api.domain.model.User;
import com.jaya.api.repository.IUserRepository;
import com.jaya.api.service.IUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<UserDTO> userDTOJson;
    @Autowired
    private JacksonTester<User> userJson;

    @MockBean
    private IUserRepository userRepository;


    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informações estão validas")
    void add() throws Exception {
        var addressDTO = new AddressDTO("Avenida dos Lirios", "Vila das Arvores", "19820000", "Taruma", "SP", null, "1219");
        var address = new Address(addressDTO);
        var userDetails = new User(null, "Joao da Silva", "email@test.com", "12345678", "47383784499", address);
        when(userRepository.save(any())).thenReturn(userDetails);

        var response = mvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDTOJson.write(new UserDTO(
                                "Joao da Silva", "email@test.com", "12345678", "47383784499", addressDTO
                )).getJson())
                )
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var userExpected = userJson.write(userDetails).getJson();
        assertThat(response.getContentAsString()).isEqualTo(userExpected);

    }


}