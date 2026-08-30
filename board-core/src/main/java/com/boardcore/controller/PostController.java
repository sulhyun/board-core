package com.boardcore.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boardcore.domain.Community;
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
	public String PostList(@PathVariable int co_num, Model model) {
		List<Community> communities = postService.getCommunityList();
		
		model.addAttribute("communities", communities);
		return "post/list";
	}
	
}
