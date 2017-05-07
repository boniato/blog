package com.boniato.index;

import lombok.RequiredArgsConstructor;
import com.boniato.config.Navigation;
import com.boniato.config.Section;
import com.boniato.post.Post;
import com.boniato.post.PostRepository;
import com.boniato.post.PostStatus;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.data.domain.ExampleMatcher.matching;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Lee on 2017. 5. 5..
 */

@Controller
@RequiredArgsConstructor
@Navigation(Section.HOME)
public class IndexController {

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/")
	public String home(@RequestParam(required = false) String q, Model model,
			@PageableDefault(size = 5, sort = "regDate", direction = Sort.Direction.DESC) Pageable pageable) {
		Example<Post> post = Example.of(new Post(q, PostStatus.Y),
				matching().withMatcher("title", ExampleMatcher.GenericPropertyMatcher::contains));
		model.addAttribute("posts", postRepository.findAll(post, pageable));
		return "index";
	}
}
