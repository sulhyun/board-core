package com.boardcore.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boardcore.dao.MemberDAO;
import com.boardcore.domain.Member;
import com.boardcore.domain.UserRole;
import com.boardcore.domain.UserState;
import com.boardcore.dto.Login;
import com.boardcore.dto.Signup;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberDAO memberDao;
	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean signup(Signup form) {
		if (form == null) {
			return false;
		}
		
		String encPw = passwordEncoder.encode(form.getPw());
		
		Member member = new Member();
		member.setMe_id(form.getId());
		member.setMe_pw(encPw);
		member.setMe_email(form.getEmail());
		member.setMe_authority(UserRole.USER.name());
		member.setMe_ms_name(UserState.ACTIVE.getDescription());
		
		try {
			// 아이디 중복, 이메일 중복일 때 예외 발생
			return memberDao.save(member);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public Member login(Login form) {
		if (form == null) {
			return null;
		}
		
		Member member = memberDao.findById(form.getId());
		if (member == null) {
			return null;
		}
		
		if (passwordEncoder.matches(form.getPw(), member.getMe_pw())) {
			return member;
		}
		
		return null;
	}

}
