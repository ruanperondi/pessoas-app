package br.com.softplan.pessoas.vo;

import org.apache.commons.lang3.StringUtils;
import br.com.softplan.pessoas.core.exceptions.NegocioException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString(of = {"descricao"})
public enum Sexo {

  M("Masculino"),

  F("Feminino"),

  N("N�o informado");

  private final String descricao;

  public static Sexo of(String descricao) throws NegocioException {
    Sexo[] values = Sexo.values();

    for (Sexo sexo : values) {
      if (StringUtils.equalsIgnoreCase(descricao, sexo.descricao)) {
        return sexo;
      }
    }

    throw new NegocioException("Sexo não encontrado: " + descricao);
  }

  public static Sexo filterOf(String sexo) {
    try {
      return of(sexo);
    } catch (Exception NegocioException) {
      return null;
    }
  }

}
