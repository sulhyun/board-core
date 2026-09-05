package com.boardcore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginForm {
	
	@NotBlank
	private String id;
	
	@NotBlank
	private String pw;
	
}
