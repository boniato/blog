package com.boniato.post;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.boniato.category.Category;
import com.boniato.comment.Comment;
import com.boniato.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Lee on 2017. 5. 5..
 */
@Data
@Entity
@ToString(exclude = { "category", "comments" })
@EqualsAndHashCode(exclude = { "category", "comments" })
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String title;

	@Lob
	@NotNull
	private String content;

	@Lob
	private String code;

	@Enumerated(EnumType.STRING)
	private PostStatus status;

	private LocalDateTime regDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<Comment> comments;

	//// FIXME: 2016. 9. 18. 異붽�
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	Post() {
	}

	public Post(Long id) {
		this.id = id;
	}

	public Post(String title, PostStatus status) {
		this.title = title;
		this.status = status;
	}

	public Post(Long id, String title, String content, String code, PostStatus status) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.code = code;
		this.status = status;
	}

	public Post(String title, String content, String code, PostStatus status) {
		this.title = title;
		this.content = content;
		this.code = code;
		this.status = status;
	}

	public Post(String title, String content, String code, PostStatus status, Category category, User user) {
		this.title = title;
		this.content = content;
		this.code = code;
		this.status = status;
		this.category = category;
		this.user = user;
	}
}