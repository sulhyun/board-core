package com.boardcore.service;

import java.util.List;

import com.boardcore.domain.Community;
import com.boardcore.domain.Member;
import com.boardcore.domain.Post;
import com.boardcore.dto.PostSaveForm;
import com.boardcore.pagination.PageMaker;
import com.boardcore.pagination.PostCriteria;

public interface PostService {

	List<Community> getCommunityList();

	List<Post> getPostList(PostCriteria cri);

	PageMaker getPageMaker(PostCriteria cri);
	
	Post getPost(int po_num);

	Post addPost(PostSaveForm form, Member user);

}
