package com.Yadeedya.Yadeedya_social_media.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Yadeedya.Yadeedya_social_media.models.Chat;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService{

	@Autowired
	ChatRepository chatRepository;
	@Override
	public Chat createChat(User reqUser, User user2) {
		
		Chat isChatexists=chatRepository.findChatByUsersId(user2, reqUser);
		
		if(isChatexists!=null) {
			return isChatexists;
			
		}
		
		Chat chat=new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimestamp(LocalDateTime.now());
		
		return chatRepository.save(chat);
	}
	

	@Override
	public Chat findChatById(Integer chatid) throws Exception {
	
		Optional<Chat> chat=chatRepository.findById(chatid);
		if(chat.isEmpty()) {
			throw new Exception("chat not found with id "+chatid);
		}
		return chat.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		
		return chatRepository.findByUserId(userId);
	}

}
