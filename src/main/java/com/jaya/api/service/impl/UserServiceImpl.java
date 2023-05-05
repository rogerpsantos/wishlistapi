package com.jaya.api.service.impl;

import com.jaya.api.domain.dto.UserDTO;
import com.jaya.api.domain.model.User;
import com.jaya.api.repository.IUserRepository;
import com.jaya.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Page<User> listAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User findById(String id) {
        return this.userRepository.findById(id).get();
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
