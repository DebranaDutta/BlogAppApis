package com.springboot.blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
