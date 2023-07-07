package com.springboot.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.Payloads.CategoryDto;
import com.springboot.blog.Services.CategoryService;
import com.springboot.blog.Utils.ApiResponse;
import com.springboot.blog.Utils.RandomIdGenerator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	// GET - Get Single Category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> GetSingleCategory(@PathVariable("categoryId") int categoryId) {
		CategoryDto fetchedCategoryDto = this.categoryService.GetCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(fetchedCategoryDto, HttpStatus.OK);
	}

	// GET - Get All Category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> GetAllCategories() {
		List<CategoryDto> categoryDtos = this.categoryService.GetAllCAtegory();
		return new ResponseEntity(categoryDtos, HttpStatus.OK);
	}

	// POST - Create Category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> CreateCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createdCategoryDto = this.categoryService.CreateCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategoryDto, HttpStatus.CREATED);
	}

	// PUT - Update Category
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> UpdateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") int categoryId) {
		CategoryDto updatedCategoryDto = this.categoryService.UpdateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategoryDto, HttpStatus.ACCEPTED);
	}

	// DELETE - Delete Category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> DeleteCategory(@PathVariable("categoryId") int categoryId) {
		this.categoryService.DeleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
	}
}
