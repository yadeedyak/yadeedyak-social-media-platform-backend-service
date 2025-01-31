package com.Yadeedya.Yadeedya_social_media.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Yadeedya.Yadeedya_social_media.models.Comment;
import com.Yadeedya.Yadeedya_social_media.models.Post;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.repository.CommetRepository;
import com.Yadeedya.Yadeedya_social_media.repository.PostRepository;

import jakarta.persistence.PostRemove;
@Service
public class CommentServiceImplementation implements CommentService {

	@Autowired
	PostService postService;
	@Autowired
	UserService userService;
	@Autowired
	CommetRepository commetRepository;
	@Autowired
	PostRepository postRepository;

	@Override
	public Comment createComment(Comment comment, Integer postid, Integer userid) throws Exception {
		User user = userService.findUserById(userid);

		Post post = postService.findPostById(postid);

		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());

		Comment savedComment = commetRepository.save(comment);
		post.getComments().add(savedComment);
		postRepository.save(post);
		return savedComment;
	}

	@Override
	public Comment likeComment(Integer commentid, Integer userid) throws Exception {
		
		Comment comment=findCommentById(commentid);
		
		User user=userService.findUserById(userid);
		
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		} else {
			comment.getLiked().remove(user);
		}
		
		return commetRepository.save(comment);
	}

	@Override
	public Comment findCommentById(Integer commentid) throws Exception {
		Optional<Comment> opt = commetRepository.findById(commentid);

		if (opt.isEmpty()) {

			throw new Exception("comment not exists");

		}

		return opt.get();
	}

}
