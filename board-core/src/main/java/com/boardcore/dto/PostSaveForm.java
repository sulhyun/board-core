package com.boardcore.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSaveForm {
	
	private String po_me_id;
	
	@NotEmpty
	private String po_title;
	
	@NotEmpty
	private String po_content;
	
	private int po_co_num;
	
}
