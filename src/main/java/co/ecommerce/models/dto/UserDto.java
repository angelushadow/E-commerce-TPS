package co.ecommerce.models.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements UserDetails{

	private Long id;

	@NotBlank(message = "name es requerido")
	private String name;

	@NotBlank(message = "email es requerido")
	private String email;

	@NotBlank(message = "password es requerido")
	private String password;

	private Date created;
	private Date modified;
	private Date lastLogin;
	private String token;
	private Boolean isActive;

	@Valid
	private List<PhoneDto> phones;
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return List.of();
	}
	
	public String getUsername() {
		 return email;
	}

}
