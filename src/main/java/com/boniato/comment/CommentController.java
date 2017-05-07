package com.boniato.comment;

import lombok.RequiredArgsConstructor;
import com.boniato.post.Post;
import com.boniato.post.PostRepository;
import com.boniato.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private PostRepository postRepository;

	@ModelAttribute
	public Post post(@ModelAttribute CommentDto commentDto) {
		return postRepository.findOne(commentDto.getPostId());
	}

	@PostMapping
	public String createComment(@ModelAttribute @Valid CommentDto commentDto, BindingResult bindingResult, Model model,
			@AuthenticationPrincipal User user) {
		if (bindingResult.hasErrors()) {
			return "post/post";
		}
		model.addAttribute("comment", commentService
				.createComment(new Comment(commentDto.getContent(), new Post(commentDto.getPostId()), user)));
		return "redirect:/posts/" + commentDto.getPostId();
	}

	@PostMapping("/{postId}/{commentId}")
	public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
		commentService.deleteComment(commentId);
		return "redirect:/posts/" + postId;
	}
}
