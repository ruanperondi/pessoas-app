package br.com.softplan.pessoas.core.jpa.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;
import br.com.softplan.pessoas.core.exceptions.NegocioException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@ToString(includeFieldNames = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class Email {

  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  @Column(name = "vl_email", length = 150)
  private String email;

  public static Email of(String value) throws NegocioException {
    if (StringUtils.isEmpty(value)) {
      return null;
    }

    Matcher matcher = EMAIL_PATTERN.matcher(value);

    if (!matcher.find()) {
      throw new NegocioException("Email inválido: " + value);
    }

    Email email = new Email();
    email.email = value;

    return email;
  }

  public static Email filterOf(String value) {
    Email email = new Email();
    email.email = value;

    return email;
  }
}
