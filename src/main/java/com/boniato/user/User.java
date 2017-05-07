package com.boniato.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import com.boniato.comment.Comment;
import com.boniato.post.Post;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2017. 5. 5..
 */

@Entity
@Getter
@ToString(exclude = { "comments", "post" })
@EqualsAndHashCode(exclude = { "comments", "post" })
public class User implements Serializable {
	@GeneratedValue
	@Id
	private Long id;

	private String email;

	private String name;

	private String github;

	private String avatarUrl;

	/* To save that who writes posts or comments */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Post> post = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<>();

	@Column
	@Lob
	private String bio;

	public User(String email, String name, String github, String avatarUrl) {
		this.email = email;
		this.name = name;
		this.github = github;
		this.avatarUrl = avatarUrl;
	}

	User() {
	}
}
