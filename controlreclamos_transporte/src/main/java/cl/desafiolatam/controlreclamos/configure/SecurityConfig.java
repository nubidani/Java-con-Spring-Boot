package cl.desafiolatam.controlreclamos.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
	 @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(authorizeRequests ->
	                authorizeRequests
	                    .requestMatchers("/css/**", "/js/**", "/registration","/").permitAll()
	                    .anyRequest().authenticated()
	            )
	            .formLogin(formLogin ->
	                formLogin
	                    .loginPage("/login")
	                    .defaultSuccessUrl("/home", true)
	                    .permitAll()
	            )
	            .logout(logout ->
	                logout
	                    .permitAll()
	            );

	        return http.build();
	    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
