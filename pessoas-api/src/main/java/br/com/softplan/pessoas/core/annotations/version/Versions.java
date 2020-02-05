package br.com.softplan.pessoas.core.annotations.version;

import br.com.softplan.pessoas.core.annotations.version.impl.VersionSpecV1;
import br.com.softplan.pessoas.core.annotations.version.impl.VersionSpecV2;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Versions {

  V1(new VersionSpecV1()),

  V2(new VersionSpecV2());

  private VersionSpec specification;

}
