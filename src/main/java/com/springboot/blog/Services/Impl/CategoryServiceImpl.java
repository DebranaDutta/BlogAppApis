package com.springboot.blog.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.blog.Entities.Category;
import com.springboot.blog.Exceptions.ResourceNotFoundException;
import com.springboot.blog.Payloads.CategoryDto;
import com.springboot.blog.Repositories.CategoryRepository;
import com.springboot.blog.Services.CategoryService;
import com.springboot.blog.Utils.RandomIdGenerator;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto CreateCategory(CategoryDto categoryDto) {
		categoryDto.setCategoryId(RandomIdGenerator.newIdGenrator());
		Category category = this.Convert_CategoryDto_to_Category(categoryDto);
		Category savedCategory = this.categoryRepository.save(category);
		return this.Convert_Category_to_CategoryDto(savedCategory);
	}

	@Override
	public CategoryDto UpdateCategory(CategoryDto categoryDto, int categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCtegory = this.categoryRepository.save(category);
		return this.Convert_Category_to_CategoryDto(updatedCtegory);
	}

	@Override
	public void DeleteCategory(int categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		this.categoryRepository.delete(category);
	}

	@Override
	public CategoryDto GetCategoryById(int categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		return this.Convert_Category_to_CategoryDto(category);
	}

	@Override
	public List<CategoryDto> GetAllCAtegory() {
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoryDto> categoryDtos = categories.stream().map(category -> this.Convert_Category_to_CategoryDto(category)).collect(Collectors.toList());
		return categoryDtos;
	}

	/* Start of conversion of Object Using ModelMapper */
	public Category Convert_CategoryDto_to_Category(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}

	public CategoryDto Convert_Category_to_CategoryDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}
	/* End of conversion of Object Using ModelMapper */
}
