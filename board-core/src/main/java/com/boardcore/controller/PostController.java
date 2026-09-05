package com.boardcore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boardcore.domain.Community;
import com.boardcore.domain.Post;
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
	public String PostList(@PathVariable int co_num, @ModelAttribute("cri") PostCriteria cri, Model model) {
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
		return "post/add";
	}
	
}
