package com.boardcore.pagination;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Criteria {
	
	protected int page = 1;			// 현재 페이지 : 기본값 1
	protected int perPageNum = 10;  // 한 페이지의 컨텐츠 갯수 : 기본값 10
	
	protected String type;			// 검색 타입
	protected String Search = "";	// 검색어 : 기본값 빈문자열 -> 전체검색
	
	public Criteria(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}
	
	public int getPageStart() {
		return (page - 1) * perPageNum;
	} // DB에서 사용할 데이터 시작 인덱스
	
}
