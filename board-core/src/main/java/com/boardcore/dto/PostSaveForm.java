package com.boardcore.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostSaveForm {
	
	private int po_co_num;
	
	private String po_me_id;
	
	@NotBlank
	private String po_title;
	
	@NotBlank
	private String po_content;
	
	private List<MultipartFile> fileList;
	
}
