package com.boardcore.service;

import com.boardcore.domain.Member;
import com.boardcore.dto.Login;
import com.boardcore.dto.Signup;

public interface MemberService {

	boolean signup(Signup member);

	Member login(Login form);

}
