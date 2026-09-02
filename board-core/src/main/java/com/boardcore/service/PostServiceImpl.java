package com.boardcore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcore.dao.PostDAO;
import com.boardcore.domain.Community;
import com.boardcore.domain.Post;
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
		log.debug("totalCount={}", totalCount);
		log.debug("pm={}", pm);
		return pm;
	}

}
