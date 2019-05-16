package org.mysoft.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String queryForUserCredentials = "select email as principal, password as credentials, true from user where email=?";
		String queryForUserRole = "select user_email as principal, role_name as role from user_roles where user_email=?";
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(queryForUserCredentials)
			.authoritiesByUsernameQuery(queryForUserRole)
			.passwordEncoder(passwordEncoder())
			.rolePrefix("ROLE_")
			;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/register","/","/about","/login","/css/**","/webjars/**").permitAll()
			//.anyRequest()
			//.authenticated()
			.antMatchers("/profile").hasAnyRole("USER","ADMIN")
			.antMatchers("/users","/addTask").hasRole("ADMIN")								
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/profile")
			.and()
			.logout()
			.logoutSuccessUrl("/login")
		;
		
	}
	
	
}
