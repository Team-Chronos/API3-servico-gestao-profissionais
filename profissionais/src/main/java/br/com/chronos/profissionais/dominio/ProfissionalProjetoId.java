package br.com.chronos.profissionais.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProfissionalProjetoId implements Serializable {

    @Column(name = "projeto_id")
    private Integer projetoId;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    public ProfissionalProjetoId() {
    }

    public ProfissionalProjetoId(Integer projetoId, Integer usuarioId) {
        this.projetoId = projetoId;
        this.usuarioId = usuarioId;
    }

    public Integer getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Integer projetoId) {
        this.projetoId = projetoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProfissionalProjetoId that = (ProfissionalProjetoId) o;
        return Objects.equals(projetoId, that.projetoId) && Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projetoId, usuarioId);
    }
}
