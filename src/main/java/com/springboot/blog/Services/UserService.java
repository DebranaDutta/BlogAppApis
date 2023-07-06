package com.springboot.blog.Services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.blog.Entities.User;
import com.springboot.blog.Payloads.UserDto;

@Component
public interface UserService {
	UserDto CreateUser(UserDto userDto);

	UserDto UpdateUSer(UserDto userDto, int userId);

	UserDto GetUserByID(int userId);

	List<UserDto> GetAllUser();

	void DeleteUser(int userId);
}
