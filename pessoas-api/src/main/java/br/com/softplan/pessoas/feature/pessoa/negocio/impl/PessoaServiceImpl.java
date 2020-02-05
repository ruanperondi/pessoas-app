package br.com.softplan.pessoas.feature.pessoa.negocio.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import br.com.softplan.pessoas.core.annotations.version.VersionSpec;
import br.com.softplan.pessoas.core.jpa.pessoa.Pessoa;
import br.com.softplan.pessoas.core.jpa.vo.CPF;
import br.com.softplan.pessoas.feature.pessoa.exceptions.PessoaExistenteException;
import br.com.softplan.pessoas.feature.pessoa.exceptions.PessoaNaoEncontradaException;
import br.com.softplan.pessoas.feature.pessoa.negocio.PessoaService;
import br.com.softplan.pessoas.feature.pessoa.repository.PessoaRepository;
import lombok.Setter;

public class PessoaServiceImpl implements PessoaService {

  @Autowired
  private PessoaRepository repository;

  @Setter
  private VersionSpec especificacaoVersao;

  @Override
  public Page<Pessoa> listarPessoas(Pessoa pessoa, int page, int size) {
    Pageable pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "idPessoa");

    ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAll().withMatcher("cpf.cpf", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

    return repository.findAll(Example.of(pessoa, customExampleMatcher), pageRequest);
  }

  @Override
  @Transactional
  public Pessoa salvar(Pessoa pessoa) throws PessoaExistenteException {
    if (repository.findByCpf(pessoa.getCpf()) != null) {
      throw new PessoaExistenteException(pessoa.getCpf());
    }

    return repository.save(pessoa);
  }

  @Override
  @Transactional
  public Pessoa editar(CPF CPF, Pessoa pessoa) throws PessoaNaoEncontradaException {
    Pessoa pessoaEncontrada = getPessoa(CPF);

    pessoaEncontrada.setNome(pessoa.getNome());
    pessoaEncontrada.setNacionalidade(pessoa.getNacionalidade());
    pessoaEncontrada.setDataNascimento(pessoa.getDataNascimento());
    pessoaEncontrada.setSexo(pessoa.getSexo());
    pessoaEncontrada.setEmail(pessoa.getEmail());

    if (especificacaoVersao != null) {
      especificacaoVersao.atualizarEntidade(pessoaEncontrada, pessoa);
    }

    return repository.save(pessoaEncontrada);
  }

  @Override
  @Transactional
  public Pessoa remover(CPF CPF) throws PessoaNaoEncontradaException {
    Pessoa pessoaEncontrada = getPessoa(CPF);

    repository.delete(pessoaEncontrada);

    return pessoaEncontrada;
  }

  private Pessoa getPessoa(CPF CPF) throws PessoaNaoEncontradaException {
    Pessoa pessoaEncontrada = repository.findByCpf(CPF);

    if (pessoaEncontrada == null) {
      throw new PessoaNaoEncontradaException(CPF);
    }

    return pessoaEncontrada;
  }
}
