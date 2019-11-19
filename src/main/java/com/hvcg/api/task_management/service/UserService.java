package com.hvcg.api.task_management.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hvcg.api.task_management.dao.SecurityRepository;
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
	
	
	// check the role
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

	
	
}

/*
@Service
public class UserService {

/*
    public Optional<User> signup(String username, String password, String firstName, String lastName) {
        LOGGER.info("New user attempting to sign in");
        Optional<User> user = Optional.empty();
        if (!userRepository.findByUsername(username).isPresent()) {
            Optional<Role> role = roleRepository.findByRoleName("ROLE_CSR");
            user = Optional.of(userRepository.save(new User(username,
                            passwordEncoder.encode(password),
                            role.get(),
                            firstName,
                            lastName)));
        }
        return user;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
*/