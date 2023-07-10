package com.springboot.blog.Services;

import java.util.List;

import com.springboot.blog.Entities.Post;
import com.springboot.blog.Payloads.PostDto;
import com.springboot.blog.Payloads.PostResponse;

public interface PostService {

	PostDto CreatePost(PostDto postDto, int userId, int categoryId);

	PostDto UpdatePostDto(PostDto postDto, int postId);

	void DeletePost(int postId);

	PostResponse GetAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);

	PostDto GetPostById(int postId);

	List<PostDto> GetPostByCategory(int categoryId);

	List<PostDto> GetPostByUser(int userId);

	List<PostDto> SearchPost(String keyword);

}
