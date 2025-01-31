package com.Yadeedya.Yadeedya_social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Yadeedya.Yadeedya_social_media.models.Chat;
import com.Yadeedya.Yadeedya_social_media.models.User;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface ChatRepository extends JpaRepository<Chat, Integer>{

	   @Query("select c from Chat c where :user member of c.users and :reqUser member of c.users")
	    Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);

	    @Query("select c from Chat c join c.users u where u.id = :userId")
	    List<Chat> findByUserId(@Param("userId") Integer userId);

	
}
