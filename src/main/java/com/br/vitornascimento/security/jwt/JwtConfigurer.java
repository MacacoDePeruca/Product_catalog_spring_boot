package com.br.vitornascimento.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
	
	@Autowired
	JwtTokenProvider tokenProvider;

	public JwtConfigurer(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}


	@Override
	public void configure(HttpSecurity builder) throws Exception {
		
		
		JwtTokenFilter customFilter = new JwtTokenFilter(tokenProvider);
		
		builder.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
		
		

	}
	

}
