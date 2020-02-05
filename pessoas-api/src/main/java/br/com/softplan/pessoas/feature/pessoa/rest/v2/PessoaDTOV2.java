package br.com.softplan.pessoas.feature.pessoa.rest.v2;

import br.com.softplan.pessoas.feature.pessoa.rest.PessoaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PessoaDTOV2 extends PessoaDTO {
 
  private String rua;
  
}
