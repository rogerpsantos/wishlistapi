package com.jaya.api.service;

import com.jaya.api.dto.UserDTO;
import com.jaya.api.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> listAll();
    User findById(String id);
    User add(UserDTO user);
    User update(User user);
    void delete (String id);
}
