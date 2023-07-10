package com.springboot.blog.Services;

import com.springboot.blog.Payloads.CommentDto;

public interface CommentService {
	CommentDto CreateComment(CommentDto commentDto, int postId, int userId);

	void DeleteComment(int commentId);
}
