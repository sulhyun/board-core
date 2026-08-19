package com.boardcore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Signup {
	
	@NotBlank
	@Size(min = 6, max = 13)
	private String id;
	
	@NotBlank
	@Size(min = 6, max = 15)
	@Pattern(regexp = "^[a-zA-Z0-9!@#$]+$")
	private String pw;
	
	@NotBlank
	private String pw2;
	
	@NotBlank
	@Email
	private String email;
	
}
