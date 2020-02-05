package br.com.softplan.pessoas.feature.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.softplan.pessoas.core.jpa.pessoa.Pessoa;
import br.com.softplan.pessoas.core.jpa.vo.CPF;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

  public Pessoa findByCpf(CPF cpf);

}
