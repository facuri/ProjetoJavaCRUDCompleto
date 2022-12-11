package br.com.dalmofacuri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private Long id;

	private String nome;

	private String email;

	private String telefone;

}
