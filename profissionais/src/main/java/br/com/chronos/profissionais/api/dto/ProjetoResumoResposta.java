package br.com.chronos.profissionais.api.dto;

public record ProjetoResumoResposta(
        int id,
        String nome,
        String codigo,
        double valorHoraBase
) {
}
