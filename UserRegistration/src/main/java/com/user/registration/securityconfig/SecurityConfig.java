package com.user.registration.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.user.registration.jwt.JwtFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter authFilter;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
	return	http
			.csrf(customizer -> customizer.disable())
			.authorizeRequests()
            .requestMatchers("/user/createUser" ,"/user/login","/user/resetpassowrd/**","/user/userdetails","user/byusername/**").permitAll()
            .requestMatchers("/error").permitAll() 
            .anyRequest().authenticated()
//			.httpBasic(Customizer.withDefaults())
	        .and()
	        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
	        .build();
    }
	
//    @Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguartion) throws Exception{
//		return authenticationConfiguartion.getAuthenticationManager();
//	}
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setPasswordEncoder(passwordEncoder());
    	provider.setUserDetailsService(userDetailsService);
    	return provider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    	return config.getAuthenticationManager();
    	
    }

}
