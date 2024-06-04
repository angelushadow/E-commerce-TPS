package co.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ecommerce.exceptions.EntityNotFoundException;
import co.ecommerce.models.dto.PurchaseDto;
import co.ecommerce.models.entities.PurchaseEntity;
import co.ecommerce.models.mappers.PurchaseMapper;
import co.ecommerce.repositories.ProductRepository;
import co.ecommerce.repositories.PurchaseRepository;
import co.ecommerce.services.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional
	public PurchaseDto save(PurchaseDto dto) {

		dto.getProducts().forEach(x -> {
			productRepository.findById(x.getId());
		});

		return PurchaseMapper.INSTANCE.entityToDto(repository.save(PurchaseMapper.INSTANCE.dtoToEntity(dto)));
	}

	@Override
	public PurchaseDto findById(Long id) throws EntityNotFoundException {

		return PurchaseMapper.INSTANCE.entityToDto(repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Venta no encontrado con id: " + id)));

	}

	@Override
	@Transactional
	public PurchaseDto update(PurchaseDto dto) throws EntityNotFoundException {
		Optional<PurchaseEntity> value = repository.findById(dto.getId());

		if (value.isPresent())

			return PurchaseMapper.INSTANCE.entityToDto(repository.save(PurchaseMapper.INSTANCE.dtoToEntity(dto)));

		throw new EntityNotFoundException("Venta no encontrado con id: " + dto.getId());
	}

	@Override
	public void delete(Long id) throws EntityNotFoundException {
		Optional<PurchaseEntity> value = repository.findById(id);

		if (value.isPresent()) {
			repository.deleteById(id);
		} else {

			throw new EntityNotFoundException("Venta no encontrado con id: " + id);
		}

	}

}
