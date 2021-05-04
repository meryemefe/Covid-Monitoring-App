package com.bilkent.covidmonitoringservice.repository;

import com.bilkent.covidmonitoringservice.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
}