package co.ecommerce.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ecommerce.exceptions.EntityNotFoundException;
import co.ecommerce.models.dto.ProductDto;
import co.ecommerce.models.entities.ProductEntity;
import co.ecommerce.models.mappers.ProductMapper;
import co.ecommerce.repositories.ProductRepository;
import co.ecommerce.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	@Transactional
	public ProductDto save(ProductDto dto) {
		dto.setActive(true);
		return ProductMapper.INSTANCE.entityToDto(repository.save(ProductMapper.INSTANCE.dtoToEntity(dto)));
	}

	@Override
	public ProductDto findById(Long id) throws EntityNotFoundException {

		return ProductMapper.INSTANCE.entityToDto(repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("producto no encontrado con id: " + id)));

	}

	@Override
	@Transactional
	public ProductDto update(ProductDto dto) throws EntityNotFoundException {
		Optional<ProductEntity> value = repository.findById(dto.getId());

		dto.setModified(new Date());
		if (value.isPresent())

			return ProductMapper.INSTANCE.entityToDto(repository.save(ProductMapper.INSTANCE.dtoToEntity(dto)));

		throw new EntityNotFoundException("Producto no encontrado con id: " + dto.getId());

	}

	@Override
	public void delete(Long id) throws EntityNotFoundException {
		Optional<ProductEntity> value = repository.findById(id);

		if (value.isPresent()) {
			repository.deleteById(id);
		} else {

			throw new EntityNotFoundException("Producto no encontrado con id: " + id);
		}

	}

}
