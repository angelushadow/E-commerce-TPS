package co.ecommerce.services;

import co.ecommerce.models.dto.PurchaseDto;
import jakarta.persistence.EntityNotFoundException;

public interface PurchaseService {
	
	PurchaseDto save(PurchaseDto dto);

	PurchaseDto findById(Long id) throws EntityNotFoundException;

	PurchaseDto update(PurchaseDto dto) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

}
