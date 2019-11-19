package com.hvcg.api.task_management.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.hvcg.api.task_management.constant.Role;
import com.hvcg.api.task_management.dao.SecurityRepository;
import com.hvcg.api.task_management.dao.StaffRepository;
import com.hvcg.api.task_management.dto.LoginDto;
import com.hvcg.api.task_management.entity.Staff;
import com.hvcg.api.task_management.entity.User;
import com.hvcg.api.task_management.service.UserService;


@RestController
@RequestMapping("/security")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StaffRepository staffRepository;
	
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
		Staff staff = staffRepository.findById(loginDto.getStaffId()).get();
		
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
	
	@PostMapping("/currentUser")
	public User changeUserDetail(@RequestBody @Valid LoginDto loginDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		User user = securityRepository.findByUsername(username).get();
		user.setUsername(loginDto.getUsername());
		user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
		securityRepository.saveAndFlush(user);
		return user;
	}

}

/*
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(@RequestBody @Valid LoginDto loginDto){
        return userService.signup(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(),
                loginDto.getLastName()).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST,"User already exists"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

}
*/