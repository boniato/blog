package com.boniato.category;

import lombok.RequiredArgsConstructor;
import lombok.*;
import com.boniato.config.Navigation;
import com.boniato.config.Section;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Lee on 2017. 5. 5..
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
@Navigation(Section.CATEGORY)
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public String categories(Pageable pageable, Model model) {
		model.addAttribute("categories", categoryService.findAll(pageable));
		return "category/list";
	}

	@GetMapping("/new")
	public String newCategory(@ModelAttribute CategoryDto categoryDto) {
		return "category/new";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("categoryDto", categoryService.findOne(id));
		return "category/edit";
	}

	@PostMapping
	public String createCategory(@ModelAttribute @Valid CategoryDto categoryDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "category/new";
		}
		categoryService.createCategory(new Category(categoryDto.getId(), categoryDto.getName()));
		return "redirect:/categories";
	}

	@PostMapping("/{id}/edit")
	public String modifyCategory(@PathVariable Long id, @ModelAttribute @Valid CategoryDto categoryDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "category/edit";
		}
		categoryService.updateCategory(new Category(id, categoryDto.getName()));
		return "redirect:/categories";
	}

	@PostMapping("/{id}/delete")
	public String deleteCategory(@PathVariable Long id) {
		categoryService.delete(id);
		return "redirect:/categories";
	}
}
