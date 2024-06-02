package com.Yadeedya.Yadeedya_social_media.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Yadeedya.Yadeedya_social_media.models.Post;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.repository.PostRepository;
import com.Yadeedya.Yadeedya_social_media.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {

	@Autowired
	PostRepository postRepository;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;

	@Override
	public Post createNewPost(Post post, Integer userid) throws Exception {
		User user = userService.findUserById(userid);
		Post newPost = new Post();

		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setCretedAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		return postRepository.save(newPost);
		 
	}

	@Override
	public String deletePost(Integer postid, Integer id) throws Exception {

		Post post = findPostById(postid);

		User user = userService.findUserById(id);

		if (post.getUser().getId() != user.getId()) {
			throw new Exception("you can't delete others posts");
		}

		postRepository.delete(post);
		return "post deleted Successfully";

	}

	@Override
	public List<Post> findPostByUserId(Integer postid) {

		return postRepository.findPostByUserId(postid);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isEmpty()) {
			throw new Exception("Post not found id :" + postId);
		}
		return post.get();
	}

	@Override
	public List<Post> findAllPost() {

		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer Userid) throws Exception {
		Post post = findPostById(postId);

		User user = userService.findUserById(Userid);

		if(user.getSavedposts().contains(post) ) {
			user.getSavedposts().remove(post);
		}else {
			user.getSavedposts().add(post);
		}
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer Userid) throws Exception {
		Post post = findPostById(postId);

		User user = userService.findUserById(Userid);

		if (post.getLiked().contains(user)) {
			post.getLiked().remove(user);

		} else {
			post.getLiked().add(user);
			
		}
		return postRepository.save(post);
	}

}
