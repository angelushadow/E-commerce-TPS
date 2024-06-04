package co.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.ecommerce.models.dto.PurchaseDto;
import co.ecommerce.services.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Purchase", description = "Ordenes de compra Api")
public class PurchaseController {
	
	
	public static final String URL_RESOURCE = "/purchase";
	public static final String URL_ID_PATH = "/{id}";
	
	@Autowired
	private PurchaseService service;

	@PostMapping(URL_RESOURCE)
	@Operation(summary = "Crear nueva compra", description = "Crea una compra nuevo en la base de datos")
	public ResponseEntity<PurchaseDto> save(@Valid @RequestBody PurchaseDto dto) {
		return ResponseEntity.ok().body(service.save(dto));
	}

	@PutMapping(URL_RESOURCE)
	@Operation(summary = "Actualizar compra", description = "Actualiza una compra de la base de datos")
	public ResponseEntity<PurchaseDto> update(@Valid @RequestBody PurchaseDto dto) {
		return ResponseEntity.ok().body(service.update(dto));
	}

	@GetMapping(URL_RESOURCE + URL_ID_PATH)
	@Operation(summary = "Encontrar una compra por Id", description = "Encuentra una compra en la base de datos por el Id de tipo long")
	public ResponseEntity<PurchaseDto> findById(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(service.findById(id));

	}

	@DeleteMapping(URL_RESOURCE + URL_ID_PATH)
	@Operation(summary = "Eliminar una compra por Id", description = "Elimina una compra de la base de datos por el Id de tipo long")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
