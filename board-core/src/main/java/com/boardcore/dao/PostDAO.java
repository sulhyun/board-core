package com.boardcore.dao;

import java.util.List;

import com.boardcore.domain.CommunityVO;
import com.boardcore.domain.FileVO;
import com.boardcore.domain.PostVO;
import com.boardcore.pagination.PostCriteria;

public interface PostDAO {

	List<CommunityVO> getCommunityList();

	List<PostVO> getPostList(PostCriteria cri);

	int getPostTotalCount(PostCriteria cri);
	
	void updateView(int po_num);

	PostVO getPost(int po_num);

	boolean addPost(PostVO form);

	boolean addFile(FileVO fileVo);

}
