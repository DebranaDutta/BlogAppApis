package com.springboot.blog.Payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDto {
	private int categoryId;
	@NotBlank(message = "categoryTitle can not be blank")
	@Size(min = 4, message = "Minimum 5 characters are needed")
	private String categoryTitle;
	@NotBlank(message = "categoryDescription can not be blank")
	@Size(min = 10, message = "Minimum 10 characters are needed")
	private String categoryDescription;
}
