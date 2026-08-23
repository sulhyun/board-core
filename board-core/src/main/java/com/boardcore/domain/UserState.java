package com.boardcore.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserState {

	ACTIVE("사용"),
	SUSPENDED("기간 정지"),
	BANNED("영구 정지");
	
	private final String description;
	
	
}
