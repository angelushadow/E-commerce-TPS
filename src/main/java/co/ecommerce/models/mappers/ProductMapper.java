package co.ecommerce.models.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.ecommerce.models.dto.ProductDto;
import co.ecommerce.models.entities.ProductEntity;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	ProductDto entityToDto(ProductEntity entity);

	ProductEntity dtoToEntity(ProductDto dto);

	List<ProductDto> dtoToEntity(List<ProductEntity> listEntity);

	List<ProductEntity> entityToDto(List<ProductDto> dto);

}
