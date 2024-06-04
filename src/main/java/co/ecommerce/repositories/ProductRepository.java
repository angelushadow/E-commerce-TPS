package co.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.ecommerce.models.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	@Query(nativeQuery = true, value = "SELECT * FROM PRODUCTS p WHERE p.active=true")
	List<ProductEntity> productIsActive();
	
	@Query(nativeQuery = true, value = "select * from purchases_products,")
	List<ProductEntity> topProduct();

}
