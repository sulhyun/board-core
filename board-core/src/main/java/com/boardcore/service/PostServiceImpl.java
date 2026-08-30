package com.boardcore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcore.dao.PostDAO;
import com.boardcore.domain.Community;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

	private final PostDAO postDao;
	
	@Override
	public List<Community> getCommunityList() {
		return postDao.getCommunityList();
	}

}
