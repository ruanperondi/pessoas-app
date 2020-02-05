package br.com.softplan.pessoas.core.jpa.pessoa;

import java.util.Date;
import br.com.softplan.pessoas.core.exceptions.NegocioException;
import br.com.softplan.pessoas.core.jpa.vo.CPF;
import br.com.softplan.pessoas.core.jpa.vo.Email;
import br.com.softplan.pessoas.vo.Sexo;

public class PessoaFactory {

  public static Pessoa of(String nome, String sexo, String cpf, String naturalidade, String nacionalidade, Date dataNascimento, String email) throws NegocioException {
    return new Pessoa(null, nome, Sexo.of(sexo), CPF.of(cpf), naturalidade, nacionalidade, dataNascimento, Email.of(email));
  }

  public static Pessoa filterOf(String nome, String sexo, String cpf, String naturalidade, String nacionalidade, Date dataNascimento, String email) {
    return new Pessoa(null, nome, Sexo.filterOf(sexo), CPF.filterOf(cpf), naturalidade, nacionalidade, dataNascimento, Email.filterOf(email));
  }
}
