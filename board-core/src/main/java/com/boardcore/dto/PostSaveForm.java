package com.boardcore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostSaveForm {
	
	private String po_me_id;
	
	@NotBlank
	private String po_title;
	
	@NotBlank
	private String po_content;
	
	private int po_co_num;
	
}
