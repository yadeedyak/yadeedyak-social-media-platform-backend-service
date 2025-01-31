package com.Yadeedya.Yadeedya_social_media.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Yadeedya.Yadeedya_social_media.config.JwtProvider;
import com.Yadeedya.Yadeedya_social_media.exceptions.UserException;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User register(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		
		User savedUser = userRepository.save(newUser);

		return savedUser;
	}

	@Override
	public User findUserById(Integer userid) throws UserException {

		Optional<User> user = userRepository.findById(userid);

		if (user.isPresent()) {
			return user.get();
		}

		throw new UserException("user not exist with userid :" + userid);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userid2) throws UserException {

		User reqUser = findUserById(reqUserId);
		User user2 = findUserById(userid2);

		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowing().add(user2.getId());

		userRepository.save(reqUser);
		userRepository.save(user2);

		return reqUser;
	}

	@Override
	public User updateUser(User user,Integer userid) throws UserException {
		Optional<User> user1 = userRepository.findById(userid);

		if (user1.isEmpty()) {
			throw new UserException("user not exist with id : " + userid);
		}

		User oldUser = user1.get();
		if (user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			oldUser.setLastName(user.getLastName());

		}
		if (user.getEmail() != null) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getGender()!=null) {
			oldUser.setGender(user.getGender());
		}
		return userRepository.save(oldUser);
	}

	@Override
	public List<User> searchUser(String qurey) {
		
		return userRepository.searchUser(qurey);
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String email=JwtProvider.getEmailFromJwtToken(jwt);
		User user=userRepository.findByEmail(email);
		
		return user;
	}

}
