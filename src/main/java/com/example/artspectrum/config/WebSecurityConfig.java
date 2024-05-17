package com.example.artspectrum.config;

import com.example.artspectrum.jwt.JwtFilter;
import com.example.artspectrum.services.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	private final JwtFilter jwtFilter;
	private static final String[] PUBLIC_ENDPOINTS = {
			"/api/v1/artspectrum/sign-up",
			"/api/v1/artspectrum/auth/login",
			"/api/v1/artspectrum/auth/token",
			
			"/api/v1/artspectrum/artists/**",
			"/api/v1/artspectrum/users/**",
			
			"/v3/api-docs/**",
			"/swagger-ui/**",
			"/swagger-ui.html"
	};
	
	private static final String[] AUTHENTICATED_ENDPOINTS = {
			"/api/v1/artspectrum/admin/**",
			"/api/v1/artspectrum/user/**",
	};
	
	
	@Bean
	public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
		return new UserDetailsServiceImp();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(PUBLIC_ENDPOINTS).permitAll()
						.requestMatchers(AUTHENTICATED_ENDPOINTS).authenticated()
				)
				.formLogin(AbstractHttpConfigurer::disable)
				.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
