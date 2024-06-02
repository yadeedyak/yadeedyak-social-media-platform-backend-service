package com.Yadeedya.Yadeedya_social_media.request;

import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.service.ChatService;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.Data;

@Data
public class CreateChatRequest {

	
	private Integer userid;

	
}
