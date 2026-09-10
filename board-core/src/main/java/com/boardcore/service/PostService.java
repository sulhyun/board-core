package com.boardcore.service;

import java.util.List;

import com.boardcore.domain.CommunityVO;
import com.boardcore.domain.MemberVO;
import com.boardcore.domain.PostVO;
import com.boardcore.dto.PostSaveForm;
import com.boardcore.pagination.PageMaker;
import com.boardcore.pagination.PostCriteria;

public interface PostService {

	List<CommunityVO> getCommunityList();

	List<PostVO> getPostList(PostCriteria cri);

	PageMaker getPageMaker(PostCriteria cri);
	
	void updateView(int po_num);
	
	PostVO getPost(int po_num);

	PostVO addPost(PostSaveForm form, MemberVO user);
	
}
