package com.divergentsl.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;


/**
 * Class for Defining Username and password
 * 
 * @author Jayant
 *
 */
@Configuration

public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationProviderr authenticationProviderr;
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	/*
	 * @Bean public UserDetailsService userDetailsService() { var userDetailsService
	 * = new InMemoryUserDetailsManager();
	 * 
	 * var user =
	 * User.withUsername("root").password("root").authorities("read").build();
	 * 
	 * userDetailsService.createUser(user);
	 * 
	 * return userDetailsService(); }
	 * 
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authorizeRequests().anyRequest().authenticated();

	}
}
