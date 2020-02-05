package br.com.softplan.pessoas.feature.pessoa.exceptions;

import br.com.softplan.pessoas.core.jpa.vo.CPF;
import lombok.Getter;

@Getter
public class PessoaNaoEncontradaException extends Exception {

  private static final long serialVersionUID = 1133095603351030778L;

  private CPF cpf;

  public PessoaNaoEncontradaException(CPF cpf) {
    this.cpf = cpf;
  }

  @Override
  public String getMessage() {
    return "Pessoa não encontrada com o CPF: " + cpf.getNumeroFormatado();
  }
}
