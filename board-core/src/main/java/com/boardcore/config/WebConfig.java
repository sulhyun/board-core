package com.boardcore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.boardcore.interceptor.MemberInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MemberInterceptor())
			.order(1)
			.addPathPatterns("/**")
			.excludePathPatterns(
				"/*.ico", "/error", "/css/**", "/js/**", "/images/**", 
				"/", "/member/login", "/member/logout", "/member/signup",
				"/post/list/*", "/post/detail/*"
			);
	}

}
