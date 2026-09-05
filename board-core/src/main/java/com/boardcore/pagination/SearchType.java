package com.boardcore.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ALL 		: 전체
 * TITLE 	: 제목
 * ID		: 아이디
 */

@Data
@AllArgsConstructor
public class SearchType {
	
	private String type;
	private String displayName;
	
}
