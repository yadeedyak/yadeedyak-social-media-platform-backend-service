package com.Yadeedya.Yadeedya_social_media.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Yadeedya.Yadeedya_social_media.models.Comment;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.service.CommentService;
import com.Yadeedya.Yadeedya_social_media.service.UserService;

@RestController
public class CommentController {

	@Autowired
	CommentService commentService;
	@Autowired
	UserService userService;
	
	@PostMapping("/api/comments/posts/{postId}")
	public Comment createComment(@RequestBody Comment comment,@RequestHeader("Authorization") String jwt,
			@PathVariable Integer postId) throws Exception {
		
		 User reqUser = userService.findUserByJwt(jwt);
		 
		Comment createdcomment= commentService.createComment(comment, postId, reqUser.getId());
		
		return createdcomment;
	}
	
	@PutMapping("/api/comments/like/post/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer commentId) throws Exception {
		
		 User reqUser = userService.findUserByJwt(jwt);
		 
		Comment likecomment= commentService.likeComment(commentId, reqUser.getId());
		
		return likecomment;
	}
	
}
