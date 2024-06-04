package co.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ecommerce.models.dto.ProductDto;
import co.ecommerce.services.ShochtakingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Shochtaking", description = "Inventario Api")
@RequestMapping("/shochtaking")
public class ShochtakingController {
	
	public static final String URL_RESOURCE = "/shochtaking";
	
	@Autowired
	private ShochtakingService service;
	
	
	@GetMapping("/all")
	@Operation(summary = "Listar todo los productos", description = "Lista todos los productos con su stock en tienda")
	public ResponseEntity<List<ProductDto>> findAll() {
		return ResponseEntity.ok().body(service.findAll());

	}
	
	@GetMapping("/active")
	@Operation(summary = "Listar productos activos", description = "Lista todos los productos activos")
	public ResponseEntity<List<ProductDto>> productIsActive() {
		return ResponseEntity.ok().body(service.productIsActive());

	}
	
	@GetMapping("/top")
	@Operation(summary = "Listar top 5 productos ", description = "Lista los productos mas vendidos ")
	public ResponseEntity<List<ProductDto>> topProduct() {
		return ResponseEntity.ok().body(service.topProduct());

	}

}
