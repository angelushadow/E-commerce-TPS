package co.ecommerce.services;

import java.util.List;

import co.ecommerce.models.dto.ProductDto;

public interface ShochtakingService {

	List<ProductDto> findAll();

	List<ProductDto> productIsActive();

	List<ProductDto> topProduct();

}
