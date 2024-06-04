package co.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ecommerce.exceptions.EntityNotFoundException;
import co.ecommerce.models.dto.PhoneDto;
import co.ecommerce.models.entities.PhoneEntity;
import co.ecommerce.models.mappers.PhoneMapper;
import co.ecommerce.repositories.PhoneRepositoy;
import co.ecommerce.services.PhoneService;
import jakarta.transaction.Transactional;

@Service
public class PhoneServiceImple implements PhoneService {

	@Autowired
	private PhoneRepositoy repository;

	@Override
	@Transactional
	public PhoneDto save(PhoneDto dto) {

		return PhoneMapper.INSTANCE.entityToDto(repository.save(PhoneMapper.INSTANCE.dtoToEntity(dto)));
	}

	@Override
	public PhoneDto findById(Long id) throws EntityNotFoundException {

		return PhoneMapper.INSTANCE.entityToDto(repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Telenofo no encontrado con id: " + id)));

	}

	@Override
	@Transactional
	public PhoneDto update(PhoneDto dto) throws EntityNotFoundException {

		Optional<PhoneEntity> value = repository.findById(dto.getId());

		if (value.isPresent())

			return PhoneMapper.INSTANCE.entityToDto(repository.save(PhoneMapper.INSTANCE.dtoToEntity(dto)));

		throw new EntityNotFoundException("Telenofo no encontrado con id: " + dto.getId());

	}

	@Override
	public void delete(Long id) throws EntityNotFoundException {
		Optional<PhoneEntity> value = repository.findById(id);

		if (value.isPresent())
			repository.deleteById(id);

		throw new EntityNotFoundException("Telenofo no encontrado con id: " + id);
	}

}
