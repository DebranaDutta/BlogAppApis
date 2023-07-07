package com.springboot.blog.Services;

import java.util.List;

import com.springboot.blog.Entities.Post;
import com.springboot.blog.Payloads.PostDto;

public interface PostService {

	PostDto CreatePost(PostDto postDto, int userId, int categoryId);

	PostDto UpdatePostDto(PostDto postDto, int postId);

	void DeletePost(int postId);

	List<Post> GetAllPost();

	Post GetPostById(int postId);

	List<Post> GetPostByCategory(int categoryId);

	List<Post> GetPostByUser(int userId);

	List<Post> SearchPost(String keyword);

}
