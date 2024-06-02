package com.Yadeedya.Yadeedya_social_media.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String caption;
	private String image;
	private String video;
	@JsonIgnore
	@ManyToOne
	private User user;
	@OneToMany
	private List<User> liked=new ArrayList<>();
	@OneToMany
	private List<Comment> comments=new ArrayList<>();
	private LocalDateTime cretedAt;
	
}
