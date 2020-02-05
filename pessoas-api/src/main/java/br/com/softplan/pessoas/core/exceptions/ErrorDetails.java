package br.com.softplan.pessoas.core.exceptions;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorDetails {

  private Date data;
  
  private String mensagem;
  
  private List<String> erros;
  
  private String detalhes;

  public ErrorDetails(Date data, String mensagem, String detalhes) {
    super();
    this.data = data;
    this.mensagem = mensagem;
    this.detalhes = detalhes;
  }

  public ErrorDetails(Date data, List<String> erros, String detalhes) {
    super();
    this.data = data;
    this.erros = erros;
    this.detalhes = detalhes;
  }

}
