package co.ecommerce.services;

import co.ecommerce.exceptions.EntityNotFoundException;
import co.ecommerce.models.dto.UserDto;

public interface UserService {

	UserDto save(UserDto dto);

	UserDto findById(Long id) throws EntityNotFoundException;

	UserDto update(UserDto dto) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

}
