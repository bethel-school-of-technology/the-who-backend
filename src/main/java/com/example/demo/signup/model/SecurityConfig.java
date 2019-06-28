package com.example.demo.signup.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.signup.model.JWTFilter;
import com.example.demo.signup.model.TokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTFilter jwtFilter = new JWTFilter(this.tokenProvider);

    // @formatter:off
		http
		  .csrf().disable()
		  .cors()
		    .and()
		  .sessionManagement()
			  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			  .and()
		//.httpBasic() // optional, if you want to access
		//  .and()     // the services from a browser
		  .authorizeRequests()
		    .antMatchers("/signup", "/login", "/public").permitAll()
		    .anyRequest().authenticated()
		    .and()
		  .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		// @formatter:on
    }

}