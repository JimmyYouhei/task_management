package com.hvcg.api.task_management.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.hvcg.api.task_management.constant.Role;
import com.hvcg.api.task_management.dao.InternalStaffRepository;
import com.hvcg.api.task_management.dao.SecurityRepository;
import com.hvcg.api.task_management.dto.LoginDto;
import com.hvcg.api.task_management.entity.InternalStaff;
import com.hvcg.api.task_management.entity.User;
import com.hvcg.api.task_management.service.UserService;



@RestController
@RequestMapping("/security")
public class UserSecurityController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private InternalStaffRepository internalStaffRepository;
	
	@Autowired
	private SecurityRepository securityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public String login(@RequestBody @Valid LoginDto loginDto) {
		
		return userService.signin(loginDto.getUsername(), loginDto.getPassword()).orElseThrow(
				() -> new HttpServerErrorException(HttpStatus.FORBIDDEN, "login faild"));
	}

	@PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
	public User signup(@RequestBody @Valid LoginDto loginDto) {
		
		
		Role role = Role.valueOf(loginDto.getRole());
		InternalStaff staff = internalStaffRepository.findById(loginDto.getStaffId()).get();
		
		if (role ==null || staff == null ) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST , "bad information input");
		} else {
			
			return userService.signup(loginDto.getUsername(), loginDto.getPassword(), staff, role)
					.orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST,"User already exists"));
		}
		
	}
	
	@GetMapping("/currentUser")
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		User user = securityRepository.findByUsername(username).get();
		return user;
	}
	
	@PatchMapping("/currentUser")
	public User changeUserDetail(@RequestBody @Valid LoginDto loginDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		User user = securityRepository.findByUsername(username).get();
		user.setUsername(loginDto.getUsername());
		user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
		securityRepository.saveAndFlush(user);
		
		authentication.setAuthenticated(false);
		return user;
	}
	
	@GetMapping("/allUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> gettAllUser(){
		
		List<User> users = securityRepository.findAll();
		return users;
		
	} 
	
	@DeleteMapping("/user/{id}")	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteUser(@PathVariable int id) {
		
		securityRepository.deleteById(id);
		
	}
	
	@PatchMapping("/user/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User changeUserDetail(@PathVariable int id , @RequestBody @Valid LoginDto loginDto) {
		
		User user = securityRepository.findById(id).get();
		user.setUsername(loginDto.getUsername());
		user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
		Role role = Role.valueOf(loginDto.getRole());
		user.setRole(role.toString());
		securityRepository.saveAndFlush(user);
		
		return user;
		
	}
	
	
}

