package com.Yadeedya.Yadeedya_social_media.service;

import java.util.List;

import com.Yadeedya.Yadeedya_social_media.models.Story;
import com.Yadeedya.Yadeedya_social_media.models.User;

public interface StroyService {

	public Story createStory(Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userid) throws Exception;
}
