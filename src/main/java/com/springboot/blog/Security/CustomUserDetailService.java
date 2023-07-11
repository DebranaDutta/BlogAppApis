package com.springboot.blog.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.blog.Entities.User;
import com.springboot.blog.Exceptions.ResourceNotFoundException;
import com.springboot.blog.Repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Loading user from database by username
		User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "email", username));
		return user;
	}

}
