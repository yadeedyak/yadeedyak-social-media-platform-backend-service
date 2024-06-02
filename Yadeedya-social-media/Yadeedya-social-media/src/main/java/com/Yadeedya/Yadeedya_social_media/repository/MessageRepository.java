package com.Yadeedya.Yadeedya_social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Yadeedya.Yadeedya_social_media.models.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

	public List<Message> findByChatId(Integer chatId);
	
}
