package br.com.softplan.pessoas.feature.pessoa.rest.impl;

import java.util.function.Function;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.softplan.pessoas.core.annotations.DefaultApiResponse;
import br.com.softplan.pessoas.core.exceptions.NegocioException;
import br.com.softplan.pessoas.core.jpa.pessoa.Pessoa;
import br.com.softplan.pessoas.core.jpa.pessoa.PessoaFactory;
import br.com.softplan.pessoas.core.jpa.vo.CPF;
import br.com.softplan.pessoas.feature.pessoa.exceptions.PessoaExistenteException;
import br.com.softplan.pessoas.feature.pessoa.exceptions.PessoaNaoEncontradaException;
import br.com.softplan.pessoas.feature.pessoa.negocio.PessoaService;
import br.com.softplan.pessoas.feature.pessoa.rest.PessoaController;
import br.com.softplan.pessoas.feature.pessoa.rest.PessoaDTO;
import lombok.Setter;

public abstract class PessoaControllerImpl<T extends PessoaDTO> implements PessoaController<T> {

  @Setter
  private PessoaService service;

  @Setter
  private Function<Pessoa, T> convertFunction;

  @Override
  @DefaultApiResponse
  @GetMapping
  public Page<T> listarPessoas(T pessoaDTO, @RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
    Pessoa pessoa = PessoaFactory.filterOf(pessoaDTO.getNome(), pessoaDTO.getSexo(), pessoaDTO.getCpf(), pessoaDTO.getNaturalidade(), pessoaDTO.getNacionalidade(), pessoaDTO.getDataNascimento(), pessoaDTO.getEmail());

    return service.listarPessoas(pessoa, page, size).map(convertFunction::apply);
  }

  @Override
  @DefaultApiResponse
  @PostMapping
  public ResponseEntity<T> salvar(@Valid @RequestBody T pessoaDTO) throws NegocioException, PessoaExistenteException {
    Pessoa pessoa = PessoaFactory.of(pessoaDTO.getNome(), pessoaDTO.getSexo(), pessoaDTO.getCpf(), pessoaDTO.getNaturalidade(), pessoaDTO.getNacionalidade(), pessoaDTO.getDataNascimento(), pessoaDTO.getEmail());

    pessoa = service.salvar(pessoa);

    return ResponseEntity.ok(this.convertFunction.apply(pessoa));
  }

  @Override
  @DefaultApiResponse
  @PutMapping("/{cpf}")
  public ResponseEntity<T> editar(@PathVariable("cpf") String cpf, @Valid @RequestBody T pessoaDTO) throws PessoaNaoEncontradaException, NegocioException {
    Pessoa pessoa = PessoaFactory.of(pessoaDTO.getNome(), pessoaDTO.getSexo(), pessoaDTO.getCpf(), pessoaDTO.getNaturalidade(), pessoaDTO.getNacionalidade(), pessoaDTO.getDataNascimento(), pessoaDTO.getEmail());

    pessoa = service.editar(CPF.of(cpf), pessoa);

    return ResponseEntity.ok(this.convertFunction.apply(pessoa));
  }

  @Override
  @DefaultApiResponse
  @DeleteMapping("/{cpf}")
  public ResponseEntity<T> remover(@PathVariable("cpf") String cpf) throws PessoaNaoEncontradaException, NegocioException {
    return ResponseEntity.ok(this.convertFunction.apply(service.remover(CPF.of(cpf))));
  }

}
