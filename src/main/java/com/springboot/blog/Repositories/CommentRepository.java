package com.springboot.blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.Entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
