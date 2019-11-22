package com.hvcg.api.task_management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hvcg.api.task_management.constant.Role;
import com.hvcg.api.task_management.dao.SecurityRepository;
import com.hvcg.api.task_management.entity.InternalStaff;
import com.hvcg.api.task_management.entity.Staff;
import com.hvcg.api.task_management.entity.User;
import com.hvcg.api.task_management.security.JwtProvider;

@Service
public class UserService {

	private SecurityRepository securityRepository;
	
	private AuthenticationManager authenticationManager;
	
	private PasswordEncoder passwordEncoder;
	
	private JwtProvider jwtProvider;

	@Autowired
	public UserService(SecurityRepository securityRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
		this.securityRepository = securityRepository;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
	}
	
	
	public Optional<String> signin (String username , String password){
		Optional<String> token = Optional.empty();
		Optional<User> user = securityRepository.findByUsername(username);
		
		if(user.isPresent()) {
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
				token = Optional.of(jwtProvider.createToken(username , user.get().getRole()));
				
			} catch (AuthenticationException e) {
				
			}
		}
		
		return token;
	}

	public Optional<User> signup(String username , String password , InternalStaff staff , Role role){
		Optional<User> user = Optional.empty();
		
		if(!securityRepository.findByUsername(username).isPresent()) {
			user = Optional.of(securityRepository.saveAndFlush(
					new User(username , passwordEncoder.encode(password) , 
							role.toString() , staff)));
		}
		
		return user;
		
	}
	
}
