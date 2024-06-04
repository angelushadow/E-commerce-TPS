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

import co.ecommerce.models.dto.UserDto;
import co.ecommerce.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
public class UserController {

	@Autowired
	private UserService userService;

	public static final String URL_RESOURCE_PUBLIC = "/new/user";
	public static final String URL_RESOURCE = "/user";
	public static final String URL_ID_PATH = "/{id}";

	@PostMapping(URL_RESOURCE_PUBLIC)
	@Operation(summary = "Crear nuevo usuario", description = "Crea un nuevo usuario en la base de datos")
	public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto dto) {
		;
		return ResponseEntity.ok().body(userService.save(dto));
	}

	@GetMapping(URL_RESOURCE + URL_ID_PATH)
	@Operation(summary = "Encontrar un usuario por Id", description = "Encuentra un usuario en la base de datos por el Id de tipo long")
	public ResponseEntity<UserDto> findById(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(userService.findById(id));
	}

	@PutMapping(URL_RESOURCE)
	@Operation(summary = "Actualizar usuario", description = "Actualiza un usuario de la base de datos")
	public ResponseEntity<HttpStatus> update(@RequestBody UserDto dto) {
		userService.update(dto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(URL_RESOURCE + URL_ID_PATH)
	@Operation(summary = "Eliminar un usuario por Id", description = "Elimina un usuario de la base de datos por el Id de tipo long")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
