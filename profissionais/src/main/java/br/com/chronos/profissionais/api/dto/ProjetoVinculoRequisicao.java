package br.com.chronos.profissionais.api.dto;

import jakarta.validation.constraints.DecimalMin;

public record ProjetoVinculoRequisicao(
        int projetoId,
        @DecimalMin("0.00") double valorHora
) {
}
