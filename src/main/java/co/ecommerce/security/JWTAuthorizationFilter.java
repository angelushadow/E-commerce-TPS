package co.ecommerce.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {



	@Value("${app.jwt.secret}")
	private String secretKey = "apijwt";

	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			if (checkJWTToken(request, response)) {
				Claims claims = validateToken(request);
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			} else {
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken).getBody();
	}

	/**
	 * Authentication method in Spring flow
	 * 
	 * @param claims
	 */
	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List) claims.get("authorities");

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}
	
	
	////////////////////////////////////////////////////

//	@Autowired
//	private JwtService jwtService;
//	private final HandlerExceptionResolver handlerExceptionResolver;
//	private final UserDetailsService userDetailsService;
//
//	public JWTAuthorizationFilter(UserDetailsService userDetailsService,
//			HandlerExceptionResolver handlerExceptionResolver) {
//		this.userDetailsService = userDetailsService;
//		this.handlerExceptionResolver = handlerExceptionResolver;
//	}
//
//	@Override
//	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
//			@NonNull FilterChain filterChain) throws ServletException, IOException {
//		final String authHeader = request.getHeader("Authorization");
//
//		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//
//		try {
//			final String jwt = authHeader.substring(7);
//			final String userEmail = jwtService.extractUsername(jwt);
//
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//			if (userEmail != null && authentication == null) {
//				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//
//				if (jwtService.isTokenValid(jwt, userDetails)) {
//					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
//							null, userDetails.getAuthorities());
//
//					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//					SecurityContextHolder.getContext().setAuthentication(authToken);
//				}
//			}
//
//			filterChain.doFilter(request, response);
//		} catch (Exception exception) {
//			handlerExceptionResolver.resolveException(request, response, null, exception);
//		}
//	}

}
