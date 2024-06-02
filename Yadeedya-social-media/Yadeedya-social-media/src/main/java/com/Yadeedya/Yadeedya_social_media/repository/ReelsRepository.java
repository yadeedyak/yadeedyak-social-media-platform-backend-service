package com.Yadeedya.Yadeedya_social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Yadeedya.Yadeedya_social_media.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels, Integer>{

	public List<Reels> findByUserId(Integer userId);
	
	
}
