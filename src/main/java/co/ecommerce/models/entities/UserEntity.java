package co.ecommerce.models.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "USERS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modified;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date lastLogin;

	private String token;

	private Boolean isActive;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "userPhone", joinColumns = {
			@JoinColumn(name = "idUser", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "idPhone", referencedColumnName = "id") })
	private List<PhoneEntity> phones;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return List.of();
	}

	@Override
	public String getUsername() {
		 return email;
	}

}
