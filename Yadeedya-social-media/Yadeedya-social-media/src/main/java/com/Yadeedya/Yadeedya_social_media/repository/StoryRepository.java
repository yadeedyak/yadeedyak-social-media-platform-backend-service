package com.Yadeedya.Yadeedya_social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Yadeedya.Yadeedya_social_media.models.Story;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface StoryRepository extends JpaRepository<Story, Integer>{

	public List<Story> findByUserId(Integer userid);
	
}
