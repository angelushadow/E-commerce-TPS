package co.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ecommerce.models.dto.ProductDto;
import co.ecommerce.models.mappers.ProductMapper;
import co.ecommerce.repositories.ProductRepository;
import co.ecommerce.repositories.PurchaseRepository;
import co.ecommerce.services.ShochtakingService;

@Service
public class ShochtakingServiceImpl implements ShochtakingService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private PurchaseRepository purchaseRepo;

	@Override
	public List<ProductDto> findAll() {
		return ProductMapper.INSTANCE.dtoToEntity(productRepo.findAll());
	}
	
	@Override
	public List<ProductDto> productIsActive() {
		return ProductMapper.INSTANCE.dtoToEntity(productRepo.productIsActive());
	}
	
	@Override
	public List<ProductDto> topProduct() {
		return ProductMapper.INSTANCE.dtoToEntity(productRepo.topProduct());
	}

}
