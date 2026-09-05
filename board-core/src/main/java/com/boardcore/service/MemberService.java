package com.boardcore.service;

import com.boardcore.domain.Member;
import com.boardcore.dto.LoginForm;
import com.boardcore.dto.SignupForm;

public interface MemberService {
	
	boolean signup(SignupForm member);

	Member login(LoginForm form);

}
