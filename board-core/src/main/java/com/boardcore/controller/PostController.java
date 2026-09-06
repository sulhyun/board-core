package com.boardcore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boardcore.domain.Community;
import com.boardcore.domain.Post;
import com.boardcore.dto.PostSaveForm;
import com.boardcore.pagination.PageMaker;
import com.boardcore.pagination.PostCriteria;
import com.boardcore.pagination.SearchType;
import com.boardcore.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@ModelAttribute("searchTypes")	
	public List<SearchType> searchTypes() {
		List<SearchType> searchTypes = new ArrayList<>();
		searchTypes.add(new SearchType("ALL", "전체"));
		searchTypes.add(new SearchType("TITLE", "제목"));
		searchTypes.add(new SearchType("ID", "아이디"));
		return searchTypes;
	}
	
	@GetMapping("/list/{co_num}")
	public String list(@PathVariable int co_num, @ModelAttribute("cri") PostCriteria cri, Model model) {
		cri.setCo_num(co_num);
		cri.setPerPageNum(5);
		
		List<Community> communityList = postService.getCommunityList();
		List<Post> postList = postService.getPostList(cri);
		PageMaker pm = postService.getPageMaker(cri);
		
		model.addAttribute("communityList", communityList);
		model.addAttribute("postList", postList);
		model.addAttribute("pm", pm);
		return "post/list";
	}
	
	@GetMapping("/detail/{po_num}")
	public String detail(@PathVariable int po_num, Model model) {
		Post post = postService.getPost(po_num);

		model.addAttribute("post", post);
		return "post/detail";
	}
	
	@GetMapping("/add/{co_num}")
	public String addForm(@PathVariable int co_num, Model model) {
		model.addAttribute("post", new Post());
		model.addAttribute("co_num", co_num);
		return "post/add";
	}
	
	@PostMapping("/add")
	public String add(@Validated @ModelAttribute("post") PostSaveForm form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		log.info("Post={}", form);
		
		if (bindingResult.hasErrors()) {
			return "post/add";
		}
		
		Post post = postService.addPost(form);
		
		if (post != null) {
			redirectAttributes.addFlashAttribute("msg", "게시글 등록에 성공하셨습니다.");
			return "redirct:/post/detail/" + post.getPo_num();
		} else {
			redirectAttributes.addFlashAttribute("msg", "게시글 등록에 실패하셨습니다.");
			return "redirect:/post/add/" + form.getPo_co_num();
		}
		
	}
	
}
