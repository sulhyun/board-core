package com.boardcore.dao;

import java.util.List;

import com.boardcore.domain.Community;
import com.boardcore.domain.Post;
import com.boardcore.pagination.PostCriteria;

public interface PostDAO {

	List<Community> getCommunityList();

	List<Post> getPostList(PostCriteria cri);

	int getPostTotalCount(PostCriteria cri);
	
	void updateView(int po_num);

	Post getPost(int po_num);

}
