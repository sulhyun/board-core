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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boardcore.constant.SessionConst;
import com.boardcore.domain.CommunityVO;
import com.boardcore.domain.MemberVO;
import com.boardcore.domain.PostVO;
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
		cri.setPerPageNum(10);
		
		List<CommunityVO> communityList = postService.getCommunityList();
		List<PostVO> postList = postService.getPostList(cri);
		PageMaker pm = postService.getPageMaker(cri);
		
		model.addAttribute("communityList", communityList);
		model.addAttribute("postList", postList);
		model.addAttribute("pm", pm);
		return "post/list";
	}
	
	@GetMapping("/detail/{po_num}")
	public String detail(@PathVariable int po_num, Model model) {
		postService.updateView(po_num);
		PostVO post = postService.getPost(po_num);

		model.addAttribute("post", post);
		return "post/detail";
	}
	
	@GetMapping("/add/{co_num}")
	public String addForm(@PathVariable int co_num, Model model) {
		PostSaveForm form = new PostSaveForm();
		form.setPo_co_num(co_num);
		
		model.addAttribute("post", form);
		return "post/add";
	}
	
	@PostMapping("/add/{co_num}")
	public String add(@PathVariable int co_num, @Validated @ModelAttribute("post") PostSaveForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, @SessionAttribute(name = SessionConst.LOGIN_MEMBER) MemberVO user) {
		form.setPo_co_num(co_num);
		
		if (bindingResult.hasErrors()) {
			return "post/add";
		}
		
		PostVO post = postService.addPost(form, user);
		
		if (post != null) {
			redirectAttributes.addFlashAttribute("msg", "게시글 등록 성공!!!");
			return "redirect:/post/detail/" + post.getPo_num();
		} else {
			redirectAttributes.addFlashAttribute("msg", "게시글 등록 실패!!!");
			return "redirect:/post/list/" + form.getPo_co_num();
		}
	}
	
}
