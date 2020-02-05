package br.com.softplan.pessoas.core.annotations.version;

import br.com.softplan.pessoas.core.jpa.pessoa.Pessoa;

public interface VersionSpec {

  void atualizarEntidade(Pessoa pessoaEncontrada, Pessoa pessoaOrigem);

}
