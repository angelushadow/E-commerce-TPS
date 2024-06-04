package co.ecommerce.services.impl;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import co.ecommerce.exceptions.EntityNotFoundException;
import co.ecommerce.exceptions.ValidateException;
import co.ecommerce.models.dto.UserDto;
import co.ecommerce.models.entities.UserEntity;
import co.ecommerce.models.mappers.UserMapper;
import co.ecommerce.repositories.UserRepository;
import co.ecommerce.security.component.WebSecurityComponent;
import co.ecommerce.services.UserService;

	
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	private UserEntity entity;

	@Autowired
	private WebSecurityComponent webSecurityComponent;

	@Value("${regex.password}")
	private String REGEX_PASSWORD;

	@Value("${regex.email}")
	private String REGEX_EMAIL;	

	@Override
	public UserDto save(UserDto dto) {
		validEmail(dto.getEmail());
		validDuplicateEmail(dto.getEmail());
		validPassword(dto.getPassword());
		dto.setToken(webSecurityComponent.getJWTToken(dto.getName()));
		dto.setIsActive(true);

		this.entity = repository.save(UserMapper.INSTANCE.dtoToEntity(dto));

		return UserMapper.INSTANCE.entityToDto(entity);
	}

	@Override
	public UserDto findById(Long id) throws EntityNotFoundException {

		return UserMapper.INSTANCE.entityToDto(repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id)));

	}

	@Override
	public UserDto update(UserDto dto) throws EntityNotFoundException {

		Optional<UserEntity> value = repository.findById(dto.getId());

		dto.setModified(new Date());
		if (value.isPresent())

			return UserMapper.INSTANCE.entityToDto(repository.save(UserMapper.INSTANCE.dtoToEntity(dto)));

		throw new EntityNotFoundException("Usuario no encontrado con id: " + dto.getId());

	}

	@Override
	public void delete(Long id) throws EntityNotFoundException {
		Optional<UserEntity> value = repository.findById(id);

		if (value.isPresent())
			repository.deleteById(id);

		throw new EntityNotFoundException("Usuario no encontrado con id: " + id);

	}

	private void validDuplicateEmail(String email) {

		Example<UserEntity> userExample = Example.of(UserEntity.builder().email(email).build());
		Optional<UserEntity> userOptional = repository.findOne(userExample);
		if (userOptional.isPresent()) {
			throw new ValidateException("Correo ya registrado: " + email);
		}
	}

	private void validPassword(String password) {

		if (!isValid(password, REGEX_PASSWORD)) {
			throw new ValidateException("Validacion de formato fallida: password ");
		}
	}

	private void validEmail(String email) {

		if (!isValid(email, REGEX_EMAIL)) {
			throw new ValidateException("Validacion de formato fallida: email ");
		}
	}

	private static boolean isValid(String value, String patern) {
		Pattern pattern = Pattern.compile(patern);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

}
