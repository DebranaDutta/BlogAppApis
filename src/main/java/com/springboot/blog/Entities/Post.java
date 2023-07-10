package com.springboot.blog.Entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {
	@Id
	private int postId;
	@Column(nullable = false, length = 100)
	private String title;
	@Column(length = 1000)
	private String content;
	@Column(length = 1000)
	private String image;
	private Date addedDate;
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	

}
