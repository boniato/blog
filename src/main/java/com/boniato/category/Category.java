package com.boniato.category;

import lombok.*;
import com.boniato.post.Post;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2017. 5. 5..
 */
@Entity
@Data
@ToString(exclude = { "post" })
@EqualsAndHashCode(exclude = { "post" })
public class Category implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private LocalDateTime regDate;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Post> post = new ArrayList<>();

	Category() {
	}

	public Category(Long id) {
		this.id = id;
	}

	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}
