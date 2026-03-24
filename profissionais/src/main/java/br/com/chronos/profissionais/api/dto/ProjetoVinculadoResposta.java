package br.com.chronos.profissionais.api.dto;

import java.math.BigDecimal;

public record ProjetoVinculadoResposta(
        Integer projetoId,
        String nomeProjeto,
        String codigoProjeto,
        BigDecimal valorHora
) {
}
