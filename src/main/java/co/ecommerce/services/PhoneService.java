package co.ecommerce.services;

import co.ecommerce.exceptions.EntityNotFoundException;
import co.ecommerce.models.dto.PhoneDto;

public interface PhoneService {
	
	public PhoneDto save(PhoneDto dto);

	public PhoneDto findById(Long id) throws EntityNotFoundException;

	public PhoneDto update(PhoneDto dto) throws EntityNotFoundException;

	public void delete(Long id) throws EntityNotFoundException;

}
