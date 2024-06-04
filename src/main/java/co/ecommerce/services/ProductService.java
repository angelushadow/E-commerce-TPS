package co.ecommerce.services;

import co.ecommerce.models.dto.ProductDto;
import jakarta.persistence.EntityNotFoundException;

public interface ProductService {

	ProductDto save(ProductDto dto);

	ProductDto findById(Long id) throws EntityNotFoundException;

	ProductDto update(ProductDto dto) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

}
