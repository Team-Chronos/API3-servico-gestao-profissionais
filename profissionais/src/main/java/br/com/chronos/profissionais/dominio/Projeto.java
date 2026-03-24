package br.com.chronos.profissionais.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "projeto")
public class Projeto {

    @Id
    private Integer id;

    @Column(nullable = false, length = 256)
    private String nome;

    @Column(nullable = false, length = 7)
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_projeto", nullable = false)
    private TipoProjeto tipoProjeto;

    @Column(name = "valor_hora_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorHoraBase;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "responsavel_id", nullable = false)
    private Integer responsavelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoProjeto getTipoProjeto() {
        return tipoProjeto;
    }

    public void setTipoProjeto(TipoProjeto tipoProjeto) {
        this.tipoProjeto = tipoProjeto;
    }

    public BigDecimal getValorHoraBase() {
        return valorHoraBase;
    }

    public void setValorHoraBase(BigDecimal valorHoraBase) {
        this.valorHoraBase = valorHoraBase;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(Integer responsavelId) {
        this.responsavelId = responsavelId;
    }
}
