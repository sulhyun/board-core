package com.boardcore.domain;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {

	private String me_id;			// 아이디
	private String me_pw;			// 비밀번호
	private String me_email;		// 이메일
	private String me_authority;	// 권한
	private int me_fail; 			// 로그인 실패 횟수
	private String me_cookie;		// 자동 로그인 쿠키/토큰
	private Date me_limit;			// 자동 로그인 만료일시
	private int me_report;			// 신고 횟수
	private String me_ms_name;		// 상태
	private Date me_stop;			// 정지일시
	
}
