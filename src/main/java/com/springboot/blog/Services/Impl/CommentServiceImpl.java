package com.springboot.blog.Services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.blog.Entities.Comment;
import com.springboot.blog.Entities.Post;
import com.springboot.blog.Entities.User;
import com.springboot.blog.Exceptions.ResourceNotFoundException;
import com.springboot.blog.Payloads.CommentDto;
import com.springboot.blog.Repositories.CommentRepository;
import com.springboot.blog.Repositories.PostRepository;
import com.springboot.blog.Repositories.UserRepository;
import com.springboot.blog.Services.CommentService;
import com.springboot.blog.Utils.RandomIdGenerator;

@Component
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto CreateComment(CommentDto commentDto, int postId, int userId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Comment comment = this.Convert_CommentDto_to_Comment(commentDto);
		comment.setId(RandomIdGenerator.newIdGenrator());
		comment.setPost(post);
		comment.setUser(user);
		Comment cretedComment = this.commentRepository.save(comment);
		return this.Convert_Comment_to_CommentDto(cretedComment);
	}

	@Override
	public void DeleteComment(int commentId) {
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		this.commentRepository.delete(comment);
	}

	/* Start of conversion of Object Using ModelMapper */
	public Comment Convert_CommentDto_to_Comment(CommentDto commentDto) {
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		return comment;
	}

	public CommentDto Convert_Comment_to_CommentDto(Comment comment) {
		CommentDto commentDto = this.modelMapper.map(comment, CommentDto.class);
		return commentDto;
	}
	/* End of conversion of Object Using ModelMapper */

}
