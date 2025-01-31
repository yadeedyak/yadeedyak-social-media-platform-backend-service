package com.Yadeedya.Yadeedya_social_media.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Yadeedya.Yadeedya_social_media.models.Story;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.repository.StoryRepository;
@Service
public class StoryServiceImplementation implements StroyService{

	@Autowired
	StoryRepository storyRepository;
	@Autowired
	UserService userService;
	@Override
	public Story createStory(Story story, User user) {
		
		
		Story createstory=new Story();
		
		createstory.setCaption(story.getCaption());
		createstory.setImage(story.getImage());
		createstory.setUser(user);
		createstory.setTimestamp(LocalDateTime.now());
		
		return storyRepository.save(createstory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userid) throws Exception {
		User user=userService.findUserById(userid);
		
		return storyRepository.findByUserId(userid);
		 
	}

}
