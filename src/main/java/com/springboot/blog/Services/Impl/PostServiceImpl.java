package com.springboot.blog.Services.Impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.blog.Entities.Category;
import com.springboot.blog.Entities.Post;
import com.springboot.blog.Entities.User;
import com.springboot.blog.Exceptions.ResourceNotFoundException;
import com.springboot.blog.Payloads.CategoryDto;
import com.springboot.blog.Payloads.PostDto;
import com.springboot.blog.Repositories.CategoryRepository;
import com.springboot.blog.Repositories.PostRepository;
import com.springboot.blog.Repositories.UserRepository;
import com.springboot.blog.Services.PostService;
import com.springboot.blog.Services.UserService;
import com.springboot.blog.Utils.RandomIdGenerator;

@Component
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@Override
	public PostDto CreatePost(PostDto postDto, int userId, int categoryId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);

		Date date = new Date();
		post.setPostId(RandomIdGenerator.newIdGenrator());
		post.setImage("default.png");
		post.setAddedDate(date);
		post.setUser(user);
		post.setCategory(category);

		Post createdPost = this.postRepository.save(post);
		PostDto createPostDto = this.Convert_Post_to_PostDto(createdPost);

		// Mapping UserDto class to userDto variable of POstDto class
		createPostDto.setUserDto(userServiceImpl.Convert_User_To_UserDTO(createdPost.getUser()));

		// Mapping CategoryDto class to categoryDto variable of POstDto class
		createPostDto.setCategoryDto(categoryServiceImpl.Convert_Category_to_CategoryDto(createdPost.getCategory()));

		return createPostDto;
	}

	@Override
	public PostDto UpdatePostDto(PostDto postDto, int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DeletePost(int postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> GetAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post GetPostById(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> GetPostByCategory(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> GetPostByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> SearchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	/* Start of conversion of Object Using ModelMapper */
	public Post Convert_PostDto_to_Post(Post postDto) {
		Post post = this.modelMapper.map(postDto, Post.class);
		return post;
	}

	public PostDto Convert_Post_to_PostDto(Post post) {
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}
	/* End of conversion of Object Using ModelMapper */
}
