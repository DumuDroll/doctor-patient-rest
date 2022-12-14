package com.dddd.doctorpatientrest.web.security;

import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.web.security.jwt.AuthEntryPointJwt;
import com.dddd.doctorpatientrest.web.security.jwt.AuthTokenFilter;
import com.dddd.doctorpatientrest.web.security.user_details.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true)
@Import(SecurityProblemSupport.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProblemSupport securityProblemSupport;

	private final AuthEntryPointJwt unauthorizedHandler;

	public WebSecurityConfig(AuthEntryPointJwt unauthorizedHandler) {
		this.unauthorizedHandler = unauthorizedHandler;
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration config = new CorsConfiguration();

		config.setAllowedOrigins(Collections.singletonList("http://localhost:4200/"));
		config.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("authorization", "Cache-Control", "content-type"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/auth/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(corsConfigurationSource())
				.and()
				.csrf().disable()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/users/icon").hasAnyRole(Constants.USER, Constants.ADMIN)
				.antMatchers("/users/*").hasRole(Constants.ADMIN)
				.antMatchers("/**").hasAnyRole(Constants.USER, Constants.ADMIN)
				.anyRequest()
				.authenticated()
		;
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling()
				.authenticationEntryPoint(securityProblemSupport)
				.accessDeniedHandler(securityProblemSupport);
	}

}
