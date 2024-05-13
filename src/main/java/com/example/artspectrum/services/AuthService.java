package com.example.artspectrum.services;

import com.example.artspectrum.entities.User;
import com.example.artspectrum.jwt.JwtAuthentication;
import com.example.artspectrum.jwt.JwtProvider;
import com.example.artspectrum.jwt.JwtRequest;
import com.example.artspectrum.jwt.JwtResponse;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final UserDetailsServiceImp userDetailsService;
	private final Map<String, String> refreshStorage = new HashMap<>();
	private final JwtProvider jwtProvider;
	
	public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
		final User user = userDetailsService.getUserByLogin(authRequest.getLogin())
				.orElseThrow(() -> new AuthException("Пользователь не найден"));
		if ((passwordEncoder.matches(authRequest.getPassword(), user.getPassword()))) {
			final String accessToken = jwtProvider.generateAccessToken(user);
			final String refreshToken = jwtProvider.generateRefreshToken(user);
			refreshStorage.put(user.getLogin(), refreshToken);
			return new JwtResponse(accessToken, refreshToken);
		} else {
			throw new AuthException("Неправильный пароль");
		}
	}
	
	public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
		if (jwtProvider.validateRefreshToken(refreshToken)) {
			final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
			final String login = claims.getSubject();
			final String saveRefreshToken = refreshStorage.get(login);
			if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
				final User user = userDetailsService.getUserByLogin(login)
						.orElseThrow(() -> new AuthException("Пользователь не найден"));
				final String accessToken = jwtProvider.generateAccessToken(user);
				return new JwtResponse(accessToken, null);
			}
		}
		return new JwtResponse(null, null);
	}
	
	public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
		if (jwtProvider.validateRefreshToken(refreshToken)) {
			final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
			final String login = claims.getSubject();
			final String saveRefreshToken = refreshStorage.get(login);
			if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
				final User user = userDetailsService.getUserByLogin(login)
						.orElseThrow(() -> new AuthException("Пользователь не найден"));
				final String accessToken = jwtProvider.generateAccessToken(user);
				final String newRefreshToken = jwtProvider.generateRefreshToken(user);
				refreshStorage.put(user.getLogin(), newRefreshToken);
				return new JwtResponse(accessToken, newRefreshToken);
			}
		}
		throw new AuthException("Невалидный JWT токен");
	}
	
	public JwtAuthentication getAuthInfo() {
		return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
	}
	
}
