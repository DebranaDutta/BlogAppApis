package com.springboot.blog.Entities;

import java.util.Random;

import org.hibernate.annotations.Columns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
	@Id
	private int id;
	@Column(nullable = false, length = 1000)
	private String name;
	private String email;
	private String password;
	private String about;
}
