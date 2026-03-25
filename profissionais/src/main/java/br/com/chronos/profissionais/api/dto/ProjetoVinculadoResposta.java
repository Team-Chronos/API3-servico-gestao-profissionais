package br.com.chronos.profissionais.api.dto;

public record ProjetoVinculadoResposta(
        int projetoId,
        String nomeProjeto,
        String codigoProjeto,
        double valorHora
) {
}
