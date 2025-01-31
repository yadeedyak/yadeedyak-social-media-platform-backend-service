package com.Yadeedya.Yadeedya_social_media.service;

import java.util.List;

import com.Yadeedya.Yadeedya_social_media.models.Chat;
import com.Yadeedya.Yadeedya_social_media.models.Message;
import com.Yadeedya.Yadeedya_social_media.models.User;

public interface MessageService {

	public Message createMessage(User user,Integer chatid,Message req) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatid) throws Exception;
	
}
