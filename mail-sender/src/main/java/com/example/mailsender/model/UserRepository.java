package com.example.mailsender.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailActivationCode(String email);

    User findByUsername(String username);
}
