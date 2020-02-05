package br.com.softplan.pessoas.feature.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.softplan.pessoas.core.annotations.DefaultApiResponse;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api/public/git")
@Validated
@Api(tags = "API de da URL do Git")
public class GithubController {

  @Value("${github.url}")
  private String urlGithub;

  @DefaultApiResponse
  @GetMapping
  public String getURL() {
    return urlGithub;
  }

}
