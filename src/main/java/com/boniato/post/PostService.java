package com.boniato.post;

import lombok.RequiredArgsConstructor;
import com.boniato.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by Lee on 2017. 5. 5..
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Post createPost(Post post) {
		post.setRegDate(LocalDateTime.now());
		return postRepository.save(post);
	}

	public Post updatePost(Long id, Post post) {
		Post oldPost = postRepository.findByIdAndStatus(id, PostStatus.Y);
		if (oldPost == null) {
			throw new NotFoundException(id + " not found");
		}

		oldPost.setContent(post.getContent());
		oldPost.setCode(post.getCode());
		oldPost.setTitle(post.getTitle());
		return oldPost;
	}

	public void deletePost(Long id) {
		Post oldPost = postRepository.findByIdAndStatus(id, PostStatus.Y);
		if (oldPost == null) {
			throw new NotFoundException(id + " not found");
		}
		oldPost.setStatus(PostStatus.N);
	}

	@Transactional(readOnly = true)
	public Post findByIdAndStatus(Long id, PostStatus status) {
		Post post = postRepository.findByIdAndStatus(id, status);
		if (post == null) {
			throw new NotFoundException(id + " not found");
		}
		return post;
	}
}
