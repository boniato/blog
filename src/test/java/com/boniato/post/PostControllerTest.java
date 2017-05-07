package com.boniato.post;

import com.boniato.category.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Lee on 2017. 5. 5..
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PostService postService;

	@Test
	public void findByPost() throws Exception {
		given(this.postService.findByIdAndStatus(anyLong(), anyObject()))
				.willReturn(new Post("力格", "牧刨明", "付农促款", PostStatus.Y));
		MvcResult mvcResult = this.mvc.perform(get("/posts/{id}", 1)).andExpect(status().isOk()).andReturn();

		Post post = (Post) mvcResult.getModelAndView().getModel().get("post");
		assertThat(post.getTitle()).isEqualTo("力格");
		assertThat(post.getContent()).isEqualTo("牧刨明");
		assertThat(post.getCode()).isEqualTo("付农促款");
		assertThat(post.getStatus()).isEqualTo(PostStatus.Y);
	}

	@Test
	public void newPost() throws Exception {
		this.mvc.perform(get("/posts/new")).andExpect(status().isOk()).andExpect(view().name("post/new")).andReturn();
	}

	@Test
	public void editPost() throws Exception {
		given(this.postService.findByIdAndStatus(anyLong(), anyObject()))
				.willReturn(new Post("力格", "牧刨明", "付农促款", PostStatus.Y));
		MvcResult mvcResult = this.mvc.perform(get("/posts/edit/{id}", 1)).andExpect(status().isOk()).andReturn();

		PostDto postDto = (PostDto) mvcResult.getModelAndView().getModel().get("editPost");
		assertThat(postDto.getTitle()).isEqualTo("力格");
		assertThat(postDto.getContent()).isEqualTo("牧刨明");
		assertThat(postDto.getCode()).isEqualTo("付农促款");
	}

	@Test
	public void editPostNotFoundException() throws Exception {
		given(this.postService.findByIdAndStatus(1L, PostStatus.Y))
				.willReturn(new Post("力格", "牧刨明", "付农促款", PostStatus.Y));
		this.mvc.perform(get("/posts/edit/{id}", 2)).andExpect(status().isNotFound());
	}

	@Test
	public void createPost() throws Exception {
		Post post = new Post(1L, "力格1", "牧刨明1", "付农促款1", PostStatus.Y);
		given(postService.createPost(any())).willReturn(post);

		this.mvc.perform(post("/posts").param("title", "力格1").param("categoryId", "1").param("content", "牧刨明1")
				.param("code", "付农促款1")).andExpect(status().isFound())
				.andExpect(header().string(HttpHeaders.LOCATION, "/posts/1"));
	}

	@Test
	public void createPostValid() throws Exception {
		this.mvc.perform(post("/posts").param("title", "力格1").param("code", "付农促款1"))
				.andExpect(view().name("post/new"));

	}

	@Test
	public void modifyPost() throws Exception {
		Post post = new Post(1L, "力格2", "牧刨明2", "付农促款2", PostStatus.Y);
		given(postService.updatePost(any(), any())).willReturn(post);

		this.mvc.perform(post("/posts/{id}/edit", 1L).param("title", "力格2").param("categoryId", "1")
				.param("content", "牧刨明2").param("code", "付农促款2")).andExpect(status().isFound())
				.andExpect(header().string(HttpHeaders.LOCATION, "/posts/1"));
	}

	@Test
	public void deletePost() throws Exception {
		doNothing().when(postService).deletePost(anyLong());
		this.mvc.perform(post("/posts/{id}/delete", 1L)).andExpect(status().isFound())
				.andExpect(header().string(HttpHeaders.LOCATION, "/#/"));

	}
}