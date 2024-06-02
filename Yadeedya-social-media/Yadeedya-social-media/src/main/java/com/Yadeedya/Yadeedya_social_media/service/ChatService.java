package com.Yadeedya.Yadeedya_social_media.service;

import java.util.List;

import com.Yadeedya.Yadeedya_social_media.models.Chat;
import com.Yadeedya.Yadeedya_social_media.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser,User user2);

	public Chat findChatById(Integer chatid) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
	
	
}
