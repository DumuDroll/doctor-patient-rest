package com.dddd.doctorpatientrest.web.security.config;

import com.dddd.doctorpatientrest.web.security.user_details.iml.MyUserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public UserDetailsService userDetailsService(){
		return new MyUserDetailsImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean("authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public TokenBasedRememberMeServices tokenBasedRememberMeServices(){
		TokenBasedRememberMeServices services = new TokenBasedRememberMeServices("jsf-spring-security",
				userDetailsService());
		services.setCookieName("remember-me");
		return services;
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
				.authorizeRequests()
				.antMatchers("/api/login").permitAll()//hasAnyAuthority("USER", "ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/api/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(successHandler())
				.failureHandler(failureHandler())
				.permitAll()
				.and()
				.logout().permitAll()
		;
	}

	private AuthenticationSuccessHandler successHandler() {
		return (httpServletRequest, httpServletResponse, authentication) -> {
			httpServletResponse.getWriter().append("OK");
			httpServletResponse.setStatus(200);
		};
	}

	private AuthenticationFailureHandler failureHandler() {
		return (httpServletRequest, httpServletResponse, e) -> {
			httpServletResponse.getWriter().append("Authentication failure");
			httpServletResponse.setStatus(401);
		};
	}
}
