package com.Yadeedya.Yadeedya_social_media.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Yadeedya.Yadeedya_social_media.models.Reels;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.repository.ReelsRepository;
import com.Yadeedya.Yadeedya_social_media.repository.UserRepository;

@Service
public class ReelsServiceImplementation implements ReelsService {

	@Autowired
	ReelsRepository reelsRepository;
	
	@Autowired
	UserService userService;
	@Override
	public Reels createReel(Reels reel, User user) {
		Reels createReel=new Reels();
		
		createReel.setTitle(reel.getTitle());
		createReel.setUser(reel.getUser());
		createReel.setVideo(reel.getVideo());
		
		return reelsRepository.save(createReel);
	}

	@Override
	public List<Reels> findAllReels() {
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUsersReel(Integer userid) throws Exception {
		userService.findUserById(userid);
		return reelsRepository.findByUserId(userid);
	}

}
