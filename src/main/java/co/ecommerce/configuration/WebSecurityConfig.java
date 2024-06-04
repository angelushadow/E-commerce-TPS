package co.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import co.ecommerce.security.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {



	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/new/user");
	}

}
