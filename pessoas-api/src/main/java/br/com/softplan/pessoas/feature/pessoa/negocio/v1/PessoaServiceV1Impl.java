package br.com.softplan.pessoas.feature.pessoa.negocio.v1;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import br.com.softplan.pessoas.core.annotations.version.Version;
import br.com.softplan.pessoas.core.annotations.version.Versions;
import br.com.softplan.pessoas.feature.pessoa.negocio.PessoaService;
import br.com.softplan.pessoas.feature.pessoa.negocio.impl.PessoaServiceImpl;

@Component
@Version(Versions.V1)
public class PessoaServiceV1Impl extends PessoaServiceImpl implements PessoaService {

  @PostConstruct
  private void init() {
    super.setEspecificacaoVersao(Versions.V1.getSpecification());
  }


}
