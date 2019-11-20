package com.hvcg.api.task_management.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hvcg.api.task_management.dao.SecurityRepository;
import com.hvcg.api.task_management.entity.User;


@Component
public class UserDetailService implements UserDetailsService{

	@Autowired
	private SecurityRepository securityRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = securityRepository.findByUsername(username).
				orElseThrow(() -> new UsernameNotFoundException("username does not exist"));
		
		return withUsername(user.getUsername())
				.password(user.getPassword())
				.authorities(user.getRole())
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}
	
	public Optional<UserDetails> loadUserByJwtToken(String jwtToken){
		if(jwtProvider.isValidToken(jwtToken)) {
			
			return Optional.of(
				withUsername(jwtProvider.getUsername(jwtToken))
				.authorities(jwtProvider.getRoles(jwtToken))
				.password("")
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build());
		}
		
		return Optional.empty();
		
	}
	
	public Optional<UserDetails> loadUserByJwtTokenAndDatabase(String jwtToken){
		if (jwtProvider.isValidToken(jwtToken)) {
			return Optional.of(loadUserByUsername(jwtProvider.getUsername(jwtToken)));
		} else {
			return Optional.empty();
		}
		
		
	}
	
	
}
