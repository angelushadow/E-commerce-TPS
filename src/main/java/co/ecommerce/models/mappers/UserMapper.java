package co.ecommerce.models.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.ecommerce.models.dto.UserDto;
import co.ecommerce.models.entities.UserEntity;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDto entityToDto(UserEntity entity);

	UserEntity dtoToEntity(UserDto dto);

	List<UserDto> entityToDto(List<UserEntity> entity);

	List<UserEntity> dtoToEntity(List<UserDto> dto);

}
