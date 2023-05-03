package com.jaya.api.impl;

import com.jaya.api.dto.UserDTO;
import com.jaya.api.model.User;
import com.jaya.api.repository.IUserRepository;
import com.jaya.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> listAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return this.userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User does not exist"));
    }

    @Override
    public User add(UserDTO data) {
        return this.userRepository.save(new User(data));
    }

    @Override
    public User update(User data) {
        return this.userRepository.save(data);
    }

    @Override
    public void delete(String id) {
        this.userRepository.deleteById(id);
    }
}
