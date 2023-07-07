package com.springboot.blog.Services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.blog.Payloads.CategoryDto;

public interface CategoryService {
	CategoryDto CreateCategory(CategoryDto categoryDto);

	CategoryDto UpdateCategory(CategoryDto categoryDto, int categoryId);

	void DeleteCategory(int categoryId);

	CategoryDto GetCategoryById(int categoryId);

	List<CategoryDto> GetAllCAtegory();
}
