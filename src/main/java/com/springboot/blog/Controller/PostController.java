package com.springboot.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.Payloads.PostDto;
import com.springboot.blog.Services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;

	// POST - Create Post
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> CreatePost(@RequestBody PostDto postDto, @PathVariable("userId") int userId, @PathVariable("categoryId") int categoryId) {
		PostDto createdPostDto = this.postService.CreatePost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPostDto,HttpStatus.OK);
	}
}
