package com.boardcore.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boardcore.domain.Community;
import com.boardcore.domain.Post;
import com.boardcore.pagination.PageMaker;
import com.boardcore.pagination.PostCriteria;
import com.boardcore.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	@GetMapping("/list/{co_num}")
	public String PostList(@PathVariable int co_num, PostCriteria cri, Model model) {
		cri.setCo_num(co_num);
		cri.setPerPageNum(5);
		
		List<Community> cList = postService.getCommunityList();
		List<Post> pList = postService.getPostList(cri);
		PageMaker pm = postService.getPageMaker(cri);
		
		model.addAttribute("cList", cList);
		model.addAttribute("pList", pList);
		model.addAttribute("pm", pm);
		return "post/list";
	}
	
}
