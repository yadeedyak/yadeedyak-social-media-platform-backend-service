package com.Yadeedya.Yadeedya_social_media.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Yadeedya.Yadeedya_social_media.models.Chat;
import com.Yadeedya.Yadeedya_social_media.models.Message;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.repository.ChatRepository;
import com.Yadeedya.Yadeedya_social_media.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Override
    public Message createMessage(User user, Integer chatid, Message req) throws Exception {
        Message message = new Message();
        Chat chat = chatService.findChatById(chatid);
        // Set message attributes
        message.setChat(chat); // Assuming req already contains the chat
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());
        // Save message
        Message savedMessage = messageRepository.save(message);
        // Add message to chat's message list
        chat.getMessages().add(savedMessage);
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatid) throws Exception {
        Chat chat = chatService.findChatById(chatid);
        return chat.getMessages();
    }

}
