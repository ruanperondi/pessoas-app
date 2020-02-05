package br.com.softplan.pessoas.core.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;

/**
 * Classe que representa a base da entidade
 *
 * @author ruan
 *
 * @param <I>
 */
@MappedSuperclass
@Getter
public abstract class BaseEntity<PK extends Serializable> implements Serializable {

  private static final long serialVersionUID = 4479453405205882703L;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "dt_atualizacao", nullable = false)
  private Date dataUltimaAtualizacao;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "dt_inclusao", nullable = false)
  private Date dataInsercao;

  @PreUpdate
  protected void preUpdate() {
    dataUltimaAtualizacao = new Date();
  }

  @PrePersist
  protected void prePersist() {
    dataInsercao = new Date();
    dataUltimaAtualizacao = new Date();
  }
}