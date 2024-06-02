package com.Yadeedya.Yadeedya_social_media.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.Yadeedya.Yadeedya_social_media.exceptions.UserException;
import com.Yadeedya.Yadeedya_social_media.models.User;

public interface UserService {
	
	public User register(User user);
	
	public User findUserById(Integer userid) throws UserException;
	public User findUserByEmail(String email);
	
	public User followUser(Integer userid, Integer userid2) throws UserException;
	public User updateUser(User user,Integer userid) throws UserException;
	public List<User> searchUser(String qurey);
	
	public User findUserByJwt(String jwt);
}
