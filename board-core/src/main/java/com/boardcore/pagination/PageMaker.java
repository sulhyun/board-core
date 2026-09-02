package com.boardcore.pagination;

import lombok.Data;

@Data
public class PageMaker {

	private int totalCount;			// 전체 컨텐츠 갯수 => 마지막 페이지 번호 구하기 위해
	private int startPage;			// 페이지네이션 시작 페이지 번호
	private int endPage;			// 페이지네이션 마지막 페이지 번호
	private boolean prev;			// 이전 버튼 비/활성화
	private boolean next;			// 다음 버튼 비/활성화
	private int displayPageNum;		// 페이지네이션에서 보여줄 페이지의 최대 번호
	private Criteria cri;
	
	public void calculate() {
		endPage = (int)(Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = endPage - displayPageNum + 1;
		
		int endPageTmp = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		
		if (endPage > endPageTmp) {
			endPage = endPageTmp;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage == endPageTmp ? false : true;
	}
	
	public PageMaker(int totalCount, int displayPageNum, Criteria cri) {
		this.totalCount = totalCount;
		this.displayPageNum = displayPageNum;
		this.cri = cri;
		calculate();
	}
	
}
