package com.springboot.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.blog.Config.AppConstants;
import com.springboot.blog.Payloads.PostDto;
import com.springboot.blog.Payloads.PostResponse;
import com.springboot.blog.Services.FileService;
import com.springboot.blog.Services.PostService;
import com.springboot.blog.Utils.ApiResponse;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// POST - Create Post
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> CreatePost(@RequestBody PostDto postDto, @PathVariable("userId") int userId, @PathVariable("categoryId") int categoryId) {
		PostDto createdPostDto = this.postService.CreatePost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.OK);
	}

	// GET - GET posts by postid
	@GetMapping("/{postid}")
	public ResponseEntity<PostDto> GetPostById(@PathVariable("postid") int postid) {
		PostDto postDto = this.postService.GetPostById(postid);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	// GET - GET posts by user
	@GetMapping("/user/{userid}")
	public ResponseEntity<List<PostDto>> GetPostByUser(@PathVariable("userid") int userid) {
		List<PostDto> postsDtos = this.postService.GetPostByUser(userid);
		return new ResponseEntity<List<PostDto>>(postsDtos, HttpStatus.OK);
	}

	// GET - GET posts by Category
	@GetMapping("/category/{categoryid}")
	public ResponseEntity<List<PostDto>> GetPostByCategory(@PathVariable("categoryid") int categoryid) {
		List<PostDto> postsDtos = this.postService.GetPostByCategory(categoryid);
		return new ResponseEntity<List<PostDto>>(postsDtos, HttpStatus.OK);
	}

	// GET - GET all posts int pageNumber, int pageSize
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> GetAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		PostResponse postResponse = this.postService.GetAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// Search
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword) {
		List<PostDto> postDtos = this.postService.SearchPost(keyword);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}

	// DELETE - Delete post by ID
	@DeleteMapping("/{postid}")
	public ResponseEntity<ApiResponse> DeletePostById(@PathVariable("postid") int postid) {
		this.postService.DeletePost(postid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
	}

	// PUT - Update post by id
	@PutMapping("/{postid}")
	public ResponseEntity<PostDto> UpdatePostById(@RequestBody PostDto postDto, @PathVariable("postid") int postid) {
		PostDto updatedPostDto = this.postService.UpdatePostDto(postDto, postid);
		return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
	}

	// Post Image upload
	/*
	 * @PostMapping("/image/upload/{postid}") public ResponseEntity<ImageResponse> UploadPostImage(@RequestParam("image") MultipartFile
	 * image, @PathVariable("postid") int postid) { String fileName = this.fileService.UploadImage(path, image); PostDto
	 * postDto=this.postService.GetPostById(postid);
	 * 
	 * return null; }
	 */

}
