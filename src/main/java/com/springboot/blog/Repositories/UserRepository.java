package com.springboot.blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.springboot.blog.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
