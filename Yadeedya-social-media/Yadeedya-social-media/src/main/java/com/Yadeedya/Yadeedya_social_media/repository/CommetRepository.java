package com.Yadeedya.Yadeedya_social_media.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Yadeedya.Yadeedya_social_media.models.Comment;

public interface CommetRepository extends JpaRepository<Comment, Integer> {
	
	

}
