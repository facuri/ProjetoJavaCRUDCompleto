package br.com.dalmofacuri.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dalmofacuri.dto.ClienteDTO;
import br.com.dalmofacuri.service.ClienteService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/clientes")
@Api(value = "API REST Clientes")
public class ClienteController implements ClienteControllerDocs {

	private ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	public List<ClienteDTO> findAll() {
		return clienteService.findAll();
	}

	@GetMapping("/{id}")
	public ClienteDTO findById(@PathVariable Long id) {
		return clienteService.findById(id);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO create(@RequestBody @Valid ClienteDTO clienteDTO) {
		return clienteService.create(clienteDTO);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ClienteDTO update(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
		return clienteService.update(id, clienteDTO);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}

}
