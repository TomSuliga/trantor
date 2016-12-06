package org.suliga.trantor.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TrantorSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	

	@Override
	protected void configure(HttpSecurity hs) throws Exception {
		hs.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/cardriver/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
			.antMatchers("/").permitAll()
			.and().httpBasic()
			.and().formLogin().loginPage("/login");
		
		//hs.csrf().disable();
		
		// add this line to use H2 web console
	    hs.headers().frameOptions().disable();
	    hs.csrf().ignoringAntMatchers("/h2-console/**", "/console/**");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(new BCryptPasswordEncoder());
		
/*		auth.inMemoryAuthentication()
			.withUser("user").password("a").roles("USER").and()
	  		.withUser("tom").password("a").roles("USER", "ADMIN", "DBA").and()
	  		.withUser("admin").password("a").roles("ADMIN").and()
			.withUser("dba").password("a").roles("DBA");*/
	}
}
