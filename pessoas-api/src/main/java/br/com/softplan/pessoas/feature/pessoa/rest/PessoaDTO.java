package br.com.softplan.pessoas.feature.pessoa.rest;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaDTO {

  @NotBlank(message = "{pessoa.nome.obrigatorio}")
  @Size(message = "{pessoa.nome.tamanho}", min = 4, max = 60)
  String nome;

  @NotBlank(message = "{pessoa.sexo.obrigatorio}")
  String sexo;

  @NotBlank(message = "{pessoa.cpf.obrigatorio}")
  String cpf;

  @Size(message = "{pessoa.naturalidade.tamanho}", min = 0, max = 50)
  String naturalidade;

  @Size(message = "{pessoa.nacionalidade.tamanho}", min = 0, max = 50)
  String nacionalidade;

  Date dataNascimento;

  @Size(message = "{pessoa.email.tamanho}", min = 0, max = 100)
  String email;

}
