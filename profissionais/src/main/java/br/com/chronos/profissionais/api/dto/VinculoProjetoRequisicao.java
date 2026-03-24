package br.com.chronos.profissionais.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record VinculoProjetoRequisicao(
        @NotNull @DecimalMin("0.00") BigDecimal valorHora
) {
}
