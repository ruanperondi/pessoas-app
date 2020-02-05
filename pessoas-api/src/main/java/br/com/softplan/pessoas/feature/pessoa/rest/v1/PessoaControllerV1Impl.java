package br.com.softplan.pessoas.feature.pessoa.rest.v1;

import java.util.function.Function;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.softplan.pessoas.core.annotations.version.Version;
import br.com.softplan.pessoas.core.annotations.version.Versions;
import br.com.softplan.pessoas.core.jpa.pessoa.Pessoa;
import br.com.softplan.pessoas.feature.pessoa.negocio.PessoaService;
import br.com.softplan.pessoas.feature.pessoa.rest.PessoaDTO;
import br.com.softplan.pessoas.feature.pessoa.rest.impl.PessoaControllerImpl;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api/v1/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(tags = "API de Pessoas - V1")
public class PessoaControllerV1Impl extends PessoaControllerImpl<PessoaDTO> {

  @Autowired
  @Version(Versions.V1)
  private PessoaService pessoaServiceV1Impl;

  @PostConstruct
  private void init() {
    super.setService(pessoaServiceV1Impl);
    super.setConvertFunction(convertFunction());
  }

  private Function<Pessoa, PessoaDTO> convertFunction() {
    return pessoa -> {

      PessoaDTO dto = new PessoaDTO();

      dto.setCpf(pessoa.getCPFFormatado());
      dto.setDataNascimento(pessoa.getDataNascimento());
      dto.setEmail(pessoa.getDescricaoEmail());
      dto.setNacionalidade(pessoa.getNacionalidade());
      dto.setNaturalidade(pessoa.getNaturalidade());
      dto.setNome(pessoa.getNome());
      dto.setSexo(pessoa.getDescricaoSexo());

      return dto;
    };
  }

}
