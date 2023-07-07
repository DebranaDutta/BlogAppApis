package com.springboot.blog.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.blog.Entities.User;
import com.springboot.blog.Exceptions.ResourceNotFoundException;
import com.springboot.blog.Payloads.UserDto;
import com.springboot.blog.Repositories.UserRepository;
import com.springboot.blog.Services.UserService;
import com.springboot.blog.Utils.RandomIdGenerator;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto CreateUser(UserDto userDto) {
		userDto.setId(RandomIdGenerator.newIdGenrator());
		User user = this.Convert_UserDto_To_User(userDto);
		User savedUser = this.userRepository.save(user);
		return this.Convert_User_To_UserDTO(savedUser);
	}

	@Override
	public UserDto UpdateUSer(UserDto userDto, int userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepository.save(user);
		return this.Convert_User_To_UserDTO(updatedUser);
	}

	@Override
	public UserDto GetUserByID(int userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return this.Convert_User_To_UserDTO(user);
	}

	@Override
	public List<UserDto> GetAllUser() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.Convert_User_To_UserDTO(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void DeleteUser(int userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepository.delete(user);
	}

	/* Start of conversion of Object manually */
	/*
	 * public User Convert_UserDtoToUser(UserDto userDto) { User user = new User(); user.setId(userDto.getId()); user.setName(userDto.getName());
	 * user.setEmail(userDto.getEmail()); user.setPassword(userDto.getPassword()); user.setAbout(userDto.getAbout()); return user; }
	 * 
	 * public UserDto Convert_UserToUserDTO(User user) { UserDto userDto = new UserDto(); userDto.setId(user.getId()); userDto.setName(user.getName());
	 * userDto.setEmail(user.getEmail()); userDto.setPassword(user.getPassword()); userDto.setAbout(user.getAbout()); return userDto; }
	 */
	/* End of conversion of Object manually */

	/* Start of conversion of Object Using ModelMapper */
	public User Convert_UserDto_To_User(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	public UserDto Convert_User_To_UserDTO(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
	/* End of conversion of Object Using ModelMapper */
}
