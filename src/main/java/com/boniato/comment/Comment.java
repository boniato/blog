package com.boniato.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import com.boniato.post.Post;
import com.boniato.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Lee on 2017. 5. 5..
 */

@Data
@Entity
@ToString(exclude = { "post" })
@EqualsAndHashCode(exclude = { "post" })
public class Comment {
	@Id
	@GeneratedValue
	private Long id;

	private String content;

	private LocalDateTime regDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID")
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	public Comment(String content, Post post, User user) {
		this.content = content;
		this.post = post;
		this.user = user;
	}

	Comment() {

	}
}
