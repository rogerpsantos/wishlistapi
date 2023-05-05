package com.jaya.api.service;

import com.jaya.api.domain.dto.UserDTO;
import com.jaya.api.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<User> listAll(Pageable pageable);
    User findById(String id);
    User add(UserDTO user);
    User update(User user);
    void delete (String id);
}
