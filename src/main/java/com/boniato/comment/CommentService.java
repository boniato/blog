package com.boniato.comment;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public Comment createComment(Comment comment) {
		comment.setRegDate(LocalDateTime.now());
		return commentRepository.save(comment);
	}

	public void deleteComment(Long commentId) {
		//ommentRepository.delete(commentId);
		commentRepository.deleteById(commentId);
	}

}
