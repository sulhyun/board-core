package com.boardcore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boardcore.dto.Signup;
import com.boardcore.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/signup")
	public String signupForm(Model model) {
		model.addAttribute("member", new Signup());
		return "member/signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Validated @ModelAttribute("member") Signup member, BindingResult bindingResult) {
		log.info("Signup Info: {}", member);
		
		if (bindingResult.hasErrors()) {
			log.info("erros: {}", bindingResult);
			return "member/signup";
		}
		
		return "redirect:/";
	}
	
}
