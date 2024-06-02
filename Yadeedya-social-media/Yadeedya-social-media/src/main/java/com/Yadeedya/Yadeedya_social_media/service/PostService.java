package com.Yadeedya.Yadeedya_social_media.service;

import java.util.List;

import com.Yadeedya.Yadeedya_social_media.models.Post;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface PostService {
	
	Post createNewPost(Post post,Integer userid) throws Exception;
	
	String deletePost(Integer postid,Integer id) throws Exception;
	
	List<Post> findPostByUserId(Integer postid);
	Post findPostById(Integer postId) throws Exception;
	List<Post> findAllPost();
	
	Post savedPost(Integer postId,Integer Userid) throws Exception;
	Post likePost(Integer postId,Integer Userid) throws Exception;
	
}
