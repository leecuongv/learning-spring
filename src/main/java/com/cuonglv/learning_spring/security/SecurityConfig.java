package com.cuonglv.learning_spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cuonglv.learning_spring.service.UserService;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private final UserService userDetailsService;
	private final JwtRequestFilter jwtRequestFilter;

	public SecurityConfig(UserService userDetailsService, JwtRequestFilter jwtRequestFilter) {
		this.userDetailsService = userDetailsService;
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("Vo day 3");
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/api/user/**", "/api/animal/**", "/api/crop/**", "/api/equipment/**",
								"/api/project/**", "/api/suplier/**")
						.hasRole("USER")
						.requestMatchers("/api/admin/**", "/api/animal/**", "/api/crop/**", "/api/equipment/**",
								"/api/project/**", "/api/suplier/**")
						.hasRole("ADMIN") // Yêu cầu quyền ADMIN
						.anyRequest().authenticated() // Các yêu cầu khác cần phải xác thực
				).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		System.out.println("Vo day 4");
		// Thêm filter JwtRequestFilter để xử lý JWT cho mỗi request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}