package br.com.softplan.pessoas.feature.pessoa.negocio;

import org.springframework.data.domain.Page;
import br.com.softplan.pessoas.core.jpa.pessoa.Pessoa;
import br.com.softplan.pessoas.core.jpa.vo.CPF;
import br.com.softplan.pessoas.feature.pessoa.exceptions.PessoaExistenteException;
import br.com.softplan.pessoas.feature.pessoa.exceptions.PessoaNaoEncontradaException;

public interface PessoaService {

  public Page<Pessoa> listarPessoas(Pessoa pessoa, int page, int size);

  public Pessoa salvar(Pessoa pessoa) throws PessoaExistenteException;

  public Pessoa editar(CPF CPF, Pessoa pessoa) throws PessoaNaoEncontradaException;

  public Pessoa remover(CPF CPF) throws PessoaNaoEncontradaException;

}
