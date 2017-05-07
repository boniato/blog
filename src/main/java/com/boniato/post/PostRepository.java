package com.boniato.post;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Lee on 2017. 5. 5..
 */
public interface PostRepository extends JpaRepository<Post, Long> {
	Post findByIdAndStatus(Long id, PostStatus status);
}
