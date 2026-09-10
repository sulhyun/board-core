package com.boardcore.service;

import com.boardcore.domain.MemberVO;
import com.boardcore.dto.LoginForm;
import com.boardcore.dto.SignupForm;

public interface MemberService {
	
	boolean signup(SignupForm member);

	MemberVO login(LoginForm form);

}
