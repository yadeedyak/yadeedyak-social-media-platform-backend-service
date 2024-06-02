package com.Yadeedya.Yadeedya_social_media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Yadeedya.Yadeedya_social_media.models.Chat;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.request.CreateChatRequest;
import com.Yadeedya.Yadeedya_social_media.service.ChatService;
import com.Yadeedya.Yadeedya_social_media.service.UserService;

@RestController
public class ChatController {

	@Autowired
	private ChatService chatService;

	@Autowired
	UserService userService;

	@PostMapping("api/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req)
			throws Exception {

		User user = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(req.getUserid());
        Chat chat = chatService.createChat(user, user2);

		return chat;
	}

	@GetMapping("api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {

		User user = userService.findUserByJwt(jwt);
		List<Chat> chats = chatService.findUsersChat(user.getId());

		return chats;
	}

}
