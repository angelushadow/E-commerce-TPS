package co.ecommerce.models.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.ecommerce.models.dto.PurchaseDto;
import co.ecommerce.models.entities.PurchaseEntity;

@Mapper
public interface PurchaseMapper {
	
	PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

	PurchaseDto entityToDto(PurchaseEntity entity);

	PurchaseEntity dtoToEntity(PurchaseDto dto);

	List<PurchaseDto> dtoToEntity(List<PurchaseEntity> listEntity);

	List<PurchaseEntity> entityToDto(List<PurchaseDto> dto);

}
