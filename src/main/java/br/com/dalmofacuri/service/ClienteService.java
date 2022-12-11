package br.com.dalmofacuri.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dalmofacuri.dto.ClienteDTO;
import br.com.dalmofacuri.exception.ClienteNotFoundException;
import br.com.dalmofacuri.mapper.ClienteMapper;
import br.com.dalmofacuri.model.Cliente;
import br.com.dalmofacuri.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private static final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;
	
	private ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		 this.clienteRepository = clienteRepository;
	}
	
	public List<ClienteDTO> findAll(){
		return clienteRepository.findAll()
				                .stream()
				                //.map((n) -> clienteMapper.toDTO(n)) 
				                .map(clienteMapper::toDTO)//referencia
				                .collect(Collectors.toList());
	}
	
	public ClienteDTO findById(Long id) {
		return clienteRepository.findById(id)
			   //.map((n) -> clienteMapper.toDTO(n))
				 .map(clienteMapper::toDTO)
			   .orElseThrow(() -> new ClienteNotFoundException(id));
	}
	
	@Transactional
	public ClienteDTO create(ClienteDTO clienteDTO) {
		Cliente clienteToCreate = clienteMapper.toModel(clienteDTO);
		Cliente createCliente = clienteRepository.save(clienteToCreate);
		
		return clienteMapper.toDTO(createCliente) ;
	}
	
	@Transactional
	public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
	    verifyExists(id);
		Cliente cli = new Cliente();
		cli.setId(id);
		clienteDTO.setId(cli.getId());
		Cliente clienteToCreate = clienteMapper.toModel(clienteDTO);
		Cliente createCliente = clienteRepository.save(clienteToCreate);
		
		return clienteMapper.toDTO(createCliente) ;
	}
	
	 @Transactional
	public void delete(Long id) {
		verifyExists(id);
		clienteRepository.deleteById(id);
	}
	
	private void verifyExists(Long id) {
		 clienteRepository.findById(id)
		                  .orElseThrow(() -> new ClienteNotFoundException(id));
	 }
	

}
