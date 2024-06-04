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

//	private final AuthenticationProvider authenticationProvider;
//	
//	@Autowired
//	private JWTAuthorizationFilter jwtAuthorizationFilter;
//
//	public WebSecurityConfig(JWTAuthorizationFilter jwtAuthorizationFilter,
//			AuthenticationProvider authenticationProvider) {
//		this.authenticationProvider = authenticationProvider;
//	}
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//		http.csrf(AbstractHttpConfigurer::disable)
//
//				.authorizeHttpRequests(request -> request.requestMatchers("/login/**", "/new/user/**")
//						.permitAll().anyRequest().authenticated())
//				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider).addFilterBefore(
//                		jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();
//	}

//    private final AuthenticationProvider authenticationProvider;
//    private final JWTAuthorizationFilter jwtAuthenticationFilter;
//
//    public WebSecurityConfig(
//        JWTAuthorizationFilter jwtAuthenticationFilter,
//        AuthenticationProvider authenticationProvider
//    ) {
//        this.authenticationProvider = authenticationProvider;
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/new/user/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

//	private static final String[] AUTH_WHITELIST = {
//	          "/v3/api-docs/**",
//	          "/swagger-ui/**",
//	          "/new/user/**",
//	  };

//	@Bean
//	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	    http.csrf(csrf -> csrf.disable())
//	            .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	            .authorizeHttpRequests(auth -> auth
//	                    .requestMatchers(AUTH_WHITELIST).permitAll()
//	                    .anyRequest().authenticated()
//	            );
//
//	    http.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));
//
////	    http.authenticationProvider(authenticationProvider());
////
////	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//	    return http.build();
//	  }
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		return http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authorizeHttpRequests(
//						reqs -> reqs.requestMatchers("/new/user").permitAll().anyRequest().authenticated())
//				.csrf(AbstractHttpConfigurer::disable).build();
//	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/new/user");
	}

}
