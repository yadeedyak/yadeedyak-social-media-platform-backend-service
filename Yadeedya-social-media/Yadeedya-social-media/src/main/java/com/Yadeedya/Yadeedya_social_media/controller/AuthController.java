package com.Yadeedya.Yadeedya_social_media.controller;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Yadeedya.Yadeedya_social_media.config.JwtProvider;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.repository.UserRepository;
import com.Yadeedya.Yadeedya_social_media.responce.AuthResponse;
import com.Yadeedya.Yadeedya_social_media.responce.LoginRequest;
import com.Yadeedya.Yadeedya_social_media.service.CustomUserDetailsService;
import com.Yadeedya.Yadeedya_social_media.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		User isexists=userRepository.findByEmail(user.getEmail());
		
		if(isexists!=null) {
			throw new Exception("user already in use");
		}
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setId(user.getId());

		User savedUser = userRepository.save(newUser);

		Authentication authentication=new UsernamePasswordAuthenticationToken( savedUser.getEmail(),savedUser.getPassword());
		String token =JwtProvider.generateToken(authentication);
		AuthResponse res=new AuthResponse(token,"register success");
		return res;
		
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginrequest) {
		
		Authentication authentication= authenticate(loginrequest.getEmail(),loginrequest.getPassword());
		String token =JwtProvider.generateToken(authentication);
		AuthResponse res=new AuthResponse(token,"Login success");
		return res;
	}

	public Authentication authenticate(String email, String password) {
		UserDetails userDetails=customUserDetailsService.loadUserByUsername(email);
		if(userDetails==null) {
			throw new BadCredentialsException("invalid username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invalid password");
		}
		return new UsernamePasswordAuthenticationToken( userDetails,null,userDetails.getAuthorities());
	}
}
