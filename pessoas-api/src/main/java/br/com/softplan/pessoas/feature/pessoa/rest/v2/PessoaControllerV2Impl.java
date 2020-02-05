package br.com.softplan.pessoas.feature.pessoa.rest.v2;

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
import br.com.softplan.pessoas.feature.pessoa.rest.impl.PessoaControllerImpl;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api/vs/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(tags = "API de Pessoas - V2")
public class PessoaControllerV2Impl extends PessoaControllerImpl<PessoaDTOV2> {

  @Autowired
  @Version(Versions.V2)
  private PessoaService pessoaServiceV2Impl;

  @PostConstruct
  private void init() {
    super.setService(pessoaServiceV2Impl);
    super.setConvertFunction(convertFunction());
  }
  
  private Function<Pessoa, PessoaDTOV2> convertFunction() {
    return pessoa -> {
      PessoaDTOV2 dto = new PessoaDTOV2();

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
