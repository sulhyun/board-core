package com.boardcore.domain;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {

	private int po_num;			// 게시글 번호
	private String po_title;	// 게시글 제목
	private String po_content;	// 게시글 내용
	private String po_me_id;	// 글쓴이
	private int po_co_num;		// 커뮤니티 번호
	private Date po_date;		// 작성일
	private int po_view; 		// 조회수
	private int po_report;		// 신고횟수
	
}
