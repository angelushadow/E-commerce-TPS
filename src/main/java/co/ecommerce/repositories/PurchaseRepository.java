package co.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.ecommerce.models.entities.ProductEntity;
import co.ecommerce.models.entities.PurchaseEntity;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

	@Query(nativeQuery = true, value = "select * from purchases_products order by products_id")
	List<ProductEntity> topProduct();
}
