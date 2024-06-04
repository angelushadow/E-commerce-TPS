package co.ecommerce.models.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.ecommerce.utils.EnumState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDto {

	private Long id;
	@NotBlank(message = "El nombre es requerido")
	private String customerName;
	private EnumState state;
	private List<ProductDto> products;
}
