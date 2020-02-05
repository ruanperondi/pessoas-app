package br.com.softplan.pessoas.feature.pessoa.rest;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.softplan.pessoas.core.exceptions.NegocioException;
import br.com.softplan.pessoas.feature.pessoa.exceptions.PessoaExistenteException;
import br.com.softplan.pessoas.feature.pessoa.exceptions.PessoaNaoEncontradaException;

public interface PessoaController<T extends PessoaDTO> {

  public Page<T> listarPessoas(T pessoaDTO, int page, int size);

  public ResponseEntity<T> salvar(@Valid @RequestBody  T pessoaDTO) throws NegocioException, PessoaExistenteException;

  public ResponseEntity<T> editar(String CPF,@Valid @RequestBody  T pessoaDTO) throws PessoaNaoEncontradaException, NegocioException;

  public ResponseEntity<T> remover(String CPF) throws PessoaNaoEncontradaException, NegocioException;
}
