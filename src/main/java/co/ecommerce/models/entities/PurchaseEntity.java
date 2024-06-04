package co.ecommerce.models.entities;

import java.util.List;

import co.ecommerce.utils.EnumState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "PURCHASES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String customerName;
	private EnumState state;

	@ManyToMany(cascade = CascadeType.MERGE)
	private List<ProductEntity> products;

}
