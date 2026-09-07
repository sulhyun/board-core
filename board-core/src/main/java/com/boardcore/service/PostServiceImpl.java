package com.boardcore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boardcore.dao.PostDAO;
import com.boardcore.domain.Community;
import com.boardcore.domain.Member;
import com.boardcore.domain.Post;
import com.boardcore.dto.PostSaveForm;
import com.boardcore.pagination.PageMaker;
import com.boardcore.pagination.PostCriteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

	private final PostDAO postDao;
	
	@Override
	public List<Community> getCommunityList() {
		return postDao.getCommunityList();
	}

	@Override
	public List<Post> getPostList(PostCriteria cri) {
		if (cri == null) {
			return null;
		}
		
		return postDao.getPostList(cri);
	}

	@Override
	public PageMaker getPageMaker(PostCriteria cri) {
		if (cri == null) {
			return null;
		}
		
		int totalCount = postDao.getPostTotalCount(cri);
		PageMaker pm = new PageMaker(totalCount, 5, cri);
		return pm;
	}

	@Override
	@Transactional
	public Post getPost(int po_num) {
		postDao.updateView(po_num);
		return postDao.getPost(po_num);
	}

	@Override
	public Post addPost(PostSaveForm form, Member user) {
		if (form == null) {
			return null;
		}
		
		Post post = new Post();
		post.setPo_co_num(form.getPo_co_num());
		post.setPo_me_id(user.getMe_id());
		post.setPo_title(form.getPo_title());
		post.setPo_content(form.getPo_content());
		
		boolean result = postDao.addPost(post);
		
		return result ? post : null;
	}

}
