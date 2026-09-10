package com.boardcore.dao;

import com.boardcore.domain.MemberVO;

public interface MemberDAO {

	boolean save(MemberVO member);

	MemberVO findById(String id);

}
