package com.jaya.api.repository;

import com.jaya.api.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {

}
