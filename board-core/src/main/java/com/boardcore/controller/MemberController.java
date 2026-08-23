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
	public String signup(@Validated @ModelAttribute("member") Signup form, BindingResult bindingResult, Model model) {
		if (!form.getPw().isBlank() && !form.getPw2().isBlank()) {
			if (!form.getPw2().equals(form.getPw())) {
				bindingResult.rejectValue("pw2", "passwordMismatch", new Object[] {}, null);
			}
		}
		
		if (bindingResult.hasErrors()) {
			log.info("errors={}", bindingResult);
			return "member/signup";
		}
		
		boolean res = memberService.signup(form);
		if(res) {
			model.addAttribute("msg", "회원가입 성공");
			model.addAttribute("url", "/");
		}else {
			model.addAttribute("msg", "회원가입 실패");
			model.addAttribute("url", "/member/signup");
		}
		
		return "msg";
	}
	
}
