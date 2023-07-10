package com.springboot.blog.Services.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.springboot.blog.Entities.Category;
import com.springboot.blog.Entities.Post;
import com.springboot.blog.Entities.User;
import com.springboot.blog.Exceptions.ResourceNotFoundException;
import com.springboot.blog.Payloads.CategoryDto;
import com.springboot.blog.Payloads.PostDto;
import com.springboot.blog.Payloads.PostResponse;
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
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		this.postRepository.save(post);
		return Convert_Post_to_PostDto(post);
	}

	@Override
	public void DeletePost(int postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		this.postRepository.delete(post);
	}

	@Override
	public PostResponse GetAllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {
		PostResponse postResponse = new PostResponse();

		Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePosts = this.postRepository.findAll(pageable);
		List<Post> posts = pagePosts.getContent();
		List<PostDto> postDtos = posts.stream().map(e -> Convert_Post_to_PostDto(e)).collect(Collectors.toList());

		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getNumberOfElements());// getTotalElemets()
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());

		return postResponse;
	}

	@Override
	public PostDto GetPostById(int postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		return Convert_Post_to_PostDto(post);
	}

	@Override
	public List<PostDto> GetPostByCategory(int categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> posts = this.postRepository.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map(post -> Convert_Post_to_PostDto(post)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> GetPostByUser(int userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		List<Post> posts = this.postRepository.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post -> Convert_Post_to_PostDto(post)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> SearchPost(String keyword) {
		List<Post> posts = this.postRepository.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map(post -> Convert_Post_to_PostDto(post)).collect(Collectors.toList());
		return postDtos;
	}

	/* Start of conversion of Object Using ModelMapper */
	public Post Convert_PostDto_to_Post(Post postDto) {
		Post post = this.modelMapper.map(postDto, Post.class);
		return post;
	}

	public PostDto Convert_Post_to_PostDto(Post post) {
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		postDto.setUserDto(userServiceImpl.Convert_User_To_UserDTO(post.getUser()));
		postDto.setCategoryDto(categoryServiceImpl.Convert_Category_to_CategoryDto(post.getCategory()));
		return postDto;
	}
	/* End of conversion of Object Using ModelMapper */
}
