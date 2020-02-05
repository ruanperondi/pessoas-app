package br.com.softplan.pessoas.core.jpa.pessoa;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import br.com.softplan.pessoas.core.jpa.BaseEntity;
import br.com.softplan.pessoas.core.jpa.vo.CPF;
import br.com.softplan.pessoas.core.jpa.vo.Email;
import br.com.softplan.pessoas.vo.Sexo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = Pessoa.TABLE_NAME)
@SequenceGenerator(name = Pessoa.GENERATOR_NAME, sequenceName = Pessoa.SEQUENCE_NAME, allocationSize = 1)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(includeFieldNames = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Pessoa extends BaseEntity<Long> {

  private static final long serialVersionUID = -9222715379697475773L;

  static final String DOMAIN = "pessoa";
  static final String TABLE_NAME = "tb_" + DOMAIN;
  static final String SEQUENCE_NAME = "seq_" + DOMAIN;
  static final String GENERATOR_NAME = "gen" + SEQUENCE_NAME;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @Column(name = "id_pessoa")
  @EqualsAndHashCode.Include
  private Long idPessoa;

  @Column(name = "vl_nome", length = 60, nullable = false)
  @NotBlank(message = "{pessoa.nome.obrigatorio}")
  @Setter
  String nome;

  @Column(name = "vl_sexo", length = 10)
  @Enumerated(EnumType.STRING)
  @Setter
  Sexo sexo;

  CPF cpf;

  @Column(name = "vl_naturalidade", length = 50)
  @Setter
  String naturalidade;

  @Column(name = "vl_nacionalidade", length = 50)
  @Setter
  String nacionalidade;

  @Temporal(TemporalType.DATE)
  @Column(name = "dt_nascimento")
  @Setter
  Date dataNascimento;

  @Setter
  Email email;

  public String getCPFFormatado() {
    return this.cpf.getNumeroFormatado();
  }

  public String getDescricaoEmail() {
    if(this.email == null) {
      return null;
    }
    
    return this.email.getEmail();
  }

  public String getDescricaoSexo() {
    return sexo.getDescricao();
  }
}
