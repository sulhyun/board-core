package com.boardcore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boardcore.dao.PostDAO;
import com.boardcore.domain.CommunityVO;
import com.boardcore.domain.FileVO;
import com.boardcore.domain.MemberVO;
import com.boardcore.domain.PostVO;
import com.boardcore.dto.PostSaveForm;
import com.boardcore.pagination.PageMaker;
import com.boardcore.pagination.PostCriteria;
import com.boardcore.utils.UploadFileUtilsV1;
import com.boardcore.utils.UploadFileUtilsV2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

	@Value("${file.dir}")
	private String fileDir;
	
	private final PostDAO postDao;
	
	@Override
	public List<CommunityVO> getCommunityList() {
		return postDao.getCommunityList();
	}

	@Override
	public List<PostVO> getPostList(PostCriteria cri) {
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
	public void updateView(int po_num) {
		postDao.updateView(po_num);
	}
	
	@Override
	public PostVO getPost(int po_num) {
		return postDao.getPost(po_num);
	}

	@Override
	public PostVO addPost(PostSaveForm form, MemberVO user) {
		if (form == null) {
			return null;
		}
		
		PostVO post = new PostVO();
		post.setPo_co_num(form.getPo_co_num());
		post.setPo_me_id(user.getMe_id());
		post.setPo_title(form.getPo_title());
		post.setPo_content(form.getPo_content());
		
		boolean result = postDao.addPost(post);
		
		if (!result) {
			return null;
		}
		
		if (form.getFileList() == null || form.getFileList().size() == 0) {
			return post;
		}
		
		for (MultipartFile file : form.getFileList()) {
			uploadFile(file, post.getPo_num());
		}
		
		return post;
	}

	private void uploadFile(MultipartFile file, int po_num) {
		try {
			if (!file.isEmpty()) {
				String fi_ori_name = file.getOriginalFilename();
				//String fi_name = UploadFileUtilsV1.uploadFile(fileDir, fi_ori_name, file.getBytes());
				String fi_name = UploadFileUtilsV2.uploadFile(fileDir, file);
				
				FileVO fileVo = new FileVO(fi_ori_name, fi_name, po_num);
				postDao.addFile(fileVo);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
