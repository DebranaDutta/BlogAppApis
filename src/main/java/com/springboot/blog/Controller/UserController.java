package com.springboot.blog.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.Payloads.UserDto;
import com.springboot.blog.Services.UserService;
import com.springboot.blog.Utils.ApiResponse;
import com.springboot.blog.Utils.RandomIdGenerator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user/")
public class UserController {
	@Autowired
	private UserService userService;

	// GET - Get User
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> GetSingleUser(@PathVariable("userId") int userId) {
		UserDto fetchedUserDto = this.userService.GetUserByID(userId);
		return new ResponseEntity<UserDto>(fetchedUserDto, HttpStatus.OK);
	}

	// GET - Get All Users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> GetAllUSers() {
		List<UserDto> userDtos = this.userService.GetAllUser();
		return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
	}

	// POST - Create User
	@PostMapping("/")
	public ResponseEntity<UserDto> CreateUser(@Valid @RequestBody UserDto userDto) {
		userDto.setId(RandomIdGenerator.newIdGenrator());
		UserDto createdUserDto = this.userService.CreateUser(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	}

	// PUT - Update USer
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> UpdateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") int userId) {
		UserDto updatedUserDto = this.userService.UpdateUSer(userDto, userId);
		return new ResponseEntity(updatedUserDto, HttpStatus.ACCEPTED);
	}

	// DELETE - Delete User
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> DeleteUser(@PathVariable("userId") int userId) {
		this.userService.DeleteUser(userId);
		return new ResponseEntity(new ApiResponse("User Dleted Successfully", true), HttpStatus.OK);
	}

}
