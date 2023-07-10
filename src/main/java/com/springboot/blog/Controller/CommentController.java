package com.springboot.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.Payloads.CommentDto;
import com.springboot.blog.Services.CommentService;
import com.springboot.blog.Utils.ApiResponse;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/user/{userid}/")
	public ResponseEntity<CommentDto> CreateComment(@RequestBody CommentDto commentDto, @PathVariable("postId") int postId, @PathVariable("userid") int userid) {
		CommentDto createdComment = this.commentService.CreateComment(commentDto, postId, userid);
		return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> DeleteComment(@PathVariable("commentId") int commentId) {
		this.commentService.DeleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
	}
}
