package com.Yadeedya.Yadeedya_social_media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Yadeedya.Yadeedya_social_media.models.Post;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.responce.ApiResponse;
import com.Yadeedya.Yadeedya_social_media.service.PostService;
import com.Yadeedya.Yadeedya_social_media.service.UserService;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	UserService userService;
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization") String jwt) throws Exception {
		  User reqUser = userService.findUserByJwt(jwt);
          Post createdPost = postService.createNewPost(post, reqUser.getId());
          return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/api/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
		  User reqUser = userService.findUserByJwt(jwt);
          
		String message=postService.deletePost(postId, reqUser.getId());
		
		ApiResponse res=new ApiResponse(message,true);
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
	@GetMapping("/api/posts/{postid}")
	public ResponseEntity<Post> findPostByHandler(@PathVariable Integer postid) throws Exception {
		
		Post post=postService.findPostById(postid);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/posts/user/{userid}")
	public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userid) {
		
		List<Post> posts=postService.findPostByUserId(userid);
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost() {
		
		List<Post> posts=postService.findAllPost(); 
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
		
	}
	
	@PutMapping("/api/posts/save/{postid}")
	public ResponseEntity<Post> savedPostByHandler(@PathVariable Integer postid,@RequestHeader("Authorization") String jwt) throws Exception {
		 User reqUser = userService.findUserByJwt(jwt);
		Post post=postService.savedPost(postid,reqUser.getId());
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/posts/like/{postid}")
	public ResponseEntity<Post> LikePostByHandler(@PathVariable Integer postid,@RequestHeader("Authorization") String jwt) throws Exception {
		 User reqUser = userService.findUserByJwt(jwt);
		Post post=postService.likePost(postid,reqUser.getId());
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
}
