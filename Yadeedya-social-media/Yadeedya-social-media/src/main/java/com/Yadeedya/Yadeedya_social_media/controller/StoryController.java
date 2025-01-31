package com.Yadeedya.Yadeedya_social_media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.oauth2.login.UserInfoEndpointDsl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Yadeedya.Yadeedya_social_media.models.Story;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.service.StoryServiceImplementation;
import com.Yadeedya.Yadeedya_social_media.service.StroyService;
import com.Yadeedya.Yadeedya_social_media.service.UserService;

@RestController
public class StoryController {

	@Autowired
	private StroyService storyService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story,@RequestHeader("Authorization") String jwt) {
		 User reqUser = userService.findUserByJwt(jwt);
		Story createStory=storyService.createStory(story, reqUser);
		
		return createStory;
	}
	
	@GetMapping("/api/story/user/{userid}")
	public List<Story> createStory(@PathVariable Integer userid,@RequestHeader("Authorization") String jwt) throws Exception {
		 User reqUser = userService.findUserByJwt(jwt);
		List<Story> stories=storyService.findStoryByUserId(userid);
		
		return stories;
	}
}
