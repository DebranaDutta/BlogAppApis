package com.springboot.blog.Payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.springboot.blog.Entities.Category;
import com.springboot.blog.Entities.Comment;
import com.springboot.blog.Entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private int postId;
	private String title;
	private String content;
	private String image;
	private Date addedDate;
	private CategoryDto categoryDto;
	private UserDto userDto;
	private Set<CommentDto> comments = new HashSet<>();
}
