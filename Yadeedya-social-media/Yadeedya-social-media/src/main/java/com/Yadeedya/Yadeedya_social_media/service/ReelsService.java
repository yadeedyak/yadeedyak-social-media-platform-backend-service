package com.Yadeedya.Yadeedya_social_media.service;

import java.util.List;

import com.Yadeedya.Yadeedya_social_media.models.Reels;
import com.Yadeedya.Yadeedya_social_media.models.User;

public interface ReelsService {
	
	public Reels createReel(Reels reel,User user);
	
	public List<Reels> findAllReels();
	public List<Reels> findUsersReel(Integer userid) throws Exception;

}
