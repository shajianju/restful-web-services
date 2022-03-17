package com.restwebservices.restfulwebservices.securiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.restwebservices.restfulwebservices.jwt.JwtRequestFilter;


@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Autowired
	private MyUserDetailService myUserDetailService;

	@Autowired
	JwtRequestFilter  filter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll()
		.anyRequest().authenticated().and().sessionManagement().
		sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}


	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
