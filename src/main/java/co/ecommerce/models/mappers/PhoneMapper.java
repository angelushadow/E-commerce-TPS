package co.ecommerce.models.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.ecommerce.models.dto.PhoneDto;
import co.ecommerce.models.entities.PhoneEntity;

@Mapper
public interface PhoneMapper {

	PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

	PhoneDto entityToDto(PhoneEntity entity);

	PhoneEntity dtoToEntity(PhoneDto dto);

	List<PhoneDto> entityToDto(List<PhoneEntity> entity);

	List<PhoneEntity> dtoToEntity(List<PhoneDto> dto);

}
