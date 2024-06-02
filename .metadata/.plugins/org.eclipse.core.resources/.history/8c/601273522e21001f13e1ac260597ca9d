package com.Yadeedya.Yadeedya_social_media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Yadeedya.Yadeedya_social_media.models.Message;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.repository.ChatRepository;
import com.Yadeedya.Yadeedya_social_media.service.MessageService;
import com.Yadeedya.Yadeedya_social_media.service.UserService;

@RestController
public class MessageController {

	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;
	
	@PostMapping("/api/messages/chat/{chatid}")
	public Message createMessage(@RequestBody Message req,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer chatid ) throws Exception {
		User user =userService.findUserByJwt(jwt);
		Message message =messageService.createMessage(user, chatid, req);
		
		
		return message;
		
		
		
	}
	
	
//	@GetMapping("/api/messages/chat/{chatid}")
//	public List<Message> findChatsMessages(
//			@RequestHeader("Authorization") String jwt,
//			@PathVariable Integer chatid ) throws Exception {
//		User user =userService.findUserByJwt(jwt);
//		List<Message> message =messageService.findChatsMessages(chatid);
//		
//		
//		return message;
//		
//		
//		
//	}
	 @GetMapping("/api/messages/chat/{chatid}")
	    public List<Message> findChatsMessages(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatid) throws Exception {
	        User user = userService.findUserByJwt(jwt);
	        List<Message> messages = messageService.findChatsMessages(chatid);
	        return messages;
	    }
	
}
