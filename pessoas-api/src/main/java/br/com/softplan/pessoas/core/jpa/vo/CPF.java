package br.com.softplan.pessoas.core.jpa.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.softplan.pessoas.core.exceptions.NegocioException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@ToString(includeFieldNames = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CPF {

  private static final CPFFormatter FORMATTER = new CPFFormatter();
  
  @Column(name = "vl_cpf", nullable = false, unique = true)
  @org.hibernate.validator.constraints.br.CPF
  private String numero;

  public static CPF of(String value) throws NegocioException {
    String cpfString = RegExUtils.removeAll(value, "\\D");

    cpfString = StringUtils.leftPad(cpfString, 11, "0");
    try {
      new CPFValidator().assertValid(cpfString);
    } catch (Exception e) {
      throw new NegocioException("CPF inválido: " + cpfString);
    }

    CPF cpf = new CPF();
    cpf.numero = cpfString;

    return cpf;
  }

  public static CPF filterOf(String cpf) {
    CPF retorno = new CPF();
    retorno.numero = cpf;

    return retorno;
  }

  public String getNumeroFormatado() {
    return FORMATTER.format(numero);
  }
  
}
