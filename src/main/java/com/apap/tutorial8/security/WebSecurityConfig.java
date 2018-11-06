package com.apap.tutorial8.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//form untuk login berada di
	//“login” dan dapat diakses siapapun. Selain pada “/login” perlu dilakukan
	//autentikasi.
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/car/**").hasAnyAuthority("DEALER")
			.antMatchers("/**/add").hasAnyAuthority("ADMIN")
			.antMatchers("/**/view").hasAnyAuthority("ADMIN")
			.antMatchers("/**/update").hasAnyAuthority("ADMIN")
			.antMatchers("/**/delete").hasAnyAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
			.permitAll();
		
	}
	
	//menyimpan autentikasi in-memory
	//dengan username “cokicoki”, password “enaksekali”, dan rolenya “USER”
	
//	@Autowired
//	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication()
//			.passwordEncoder(encoder())
//			.withUser("cokicoki").password(encoder().encode("enaksekali"))
//			.roles("USER");
//	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	
}
