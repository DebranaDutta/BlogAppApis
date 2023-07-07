package com.springboot.blog.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.Entities.Category;
import com.springboot.blog.Entities.Post;
import com.springboot.blog.Entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
}
