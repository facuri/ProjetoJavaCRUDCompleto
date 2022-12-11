package br.com.dalmofacuri.builder;

import br.com.dalmofacuri.dto.ClienteDTO;
import lombok.Builder;

@Builder
public class ClienteDTOBuilder {
	
	@Builder.Default
	private final Long id = 1L;
	
	 
	private final String nome = "Dalmo Facuri";

	private final String email = "dalmo@terra.com.br";

	private final String telefone = "3651-3895";
	
	public ClienteDTO buildClienteDTO() {
		return new ClienteDTO(id,
				              nome,
				              email,
				              telefone);
	}

}
