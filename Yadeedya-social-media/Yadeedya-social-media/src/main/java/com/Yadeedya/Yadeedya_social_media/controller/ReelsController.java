package com.Yadeedya.Yadeedya_social_media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Yadeedya.Yadeedya_social_media.models.Reels;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.service.ReelsService;
import com.Yadeedya.Yadeedya_social_media.service.UserService;

@RestController
public class ReelsController {

	@Autowired
	private ReelsService reelsService;
	@Autowired
	UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) {
		
		 User reqUser = userService.findUserByJwt(jwt);
		 Reels createdReels=reelsService.createReel(reel, reqUser);
		 return createdReels;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
		
		 
		 List<Reels>reels=reelsService.findAllReels();
		 return reels;
	}
	@GetMapping("/api/ree;s/user/{userid}")
	public List<Reels> findUsersReels(@PathVariable Integer userid) throws Exception {
		
		 
		 List<Reels>reels=reelsService.findUsersReel(userid);
		 return reels;
	}
 
}
