package com.boardcore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boardcore.constant.SessionConst;
import com.boardcore.domain.MemberVO;
import com.boardcore.dto.LoginForm;
import com.boardcore.dto.SignupForm;
import com.boardcore.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/signup")
	public String signupForm(Model model) {
		model.addAttribute("member", new SignupForm());
		return "member/signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Validated @ModelAttribute("member") SignupForm form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (!form.getPw().isBlank() && !form.getPw2().isBlank()) {
			if (!form.getPw2().equals(form.getPw())) {
				bindingResult.rejectValue("pw2", "mismatch", new Object[] {}, null);
			}
		}
		
		if (bindingResult.hasErrors()) {
			return "member/signup";
		}
		
		boolean res = memberService.signup(form);
		
		if (res) {
			redirectAttributes.addFlashAttribute("msg", "회원가입 성공!!!");
			return "redirect:/";
		} else {
			redirectAttributes.addFlashAttribute("msg", "회원가입 실패!!!");
			return "redirect:/member/signup";
		}
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("member", new LoginForm());
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(@Validated @ModelAttribute("member") LoginForm form, BindingResult bindingResult, 
			@RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "member/login";
		}
		
		MemberVO member = memberService.login(form);
		if (member == null) {
			bindingResult.reject("loginFail", new Object[] {}, null);
			return "member/login";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, member);
		
		return "redirect:" + redirectURL;
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
}
