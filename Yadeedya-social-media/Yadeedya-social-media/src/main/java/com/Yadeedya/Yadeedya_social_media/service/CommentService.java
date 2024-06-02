package com.Yadeedya.Yadeedya_social_media.service;

import com.Yadeedya.Yadeedya_social_media.models.Comment;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface CommentService {

	public Comment createComment(Comment comment,Integer postid,Integer userid) throws Exception;
	public Comment findCommentById(Integer commentid) throws Exception;
	public Comment likeComment(Integer commentid,Integer userid) throws Exception;
	
	

}
