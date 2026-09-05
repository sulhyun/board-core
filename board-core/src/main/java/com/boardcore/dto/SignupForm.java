package com.boardcore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupForm {
	
	@NotBlank
	@Pattern(regexp = "^\\w{6,13}$|^$")
	private String id;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9!@#$]{6,15}$|^$")
	private String pw;
	
	private String pw2;
	
	@NotBlank
	@Email
	private String email;
	
}
