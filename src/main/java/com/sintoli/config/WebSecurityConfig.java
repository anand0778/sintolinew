package com.sintoli.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sintoli.security.CustomUserDetailsService;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	        @Autowired
        private   CustomUserDetailsService userDetailsService;
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/login","/request").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication()
//		    .withUser("amit").password("12345").authorities("admin")
//		    .and()
//		    .passwordEncoder(NoOpPasswordEncoder.getInstance());
		
//		InMemoryUserDetailsManager userDetailsService =new InMemoryUserDetailsManager();
//	UserDetails user1=User.withUsername("amit").password("12345").authorities("admin").build();
//	UserDetails user2=User.withUsername("sumit").password("12345").authorities("user").build();
//	userDetailsService.createUser(user1);
//	userDetailsService.createUser(user2);
	auth.userDetailsService(userDetailsService);
	
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
}
	@Bean
	public	AuthenticationManager authenticationManagerBean() throws Exception{
	return super.authenticationManagerBean();
		
	}
}
