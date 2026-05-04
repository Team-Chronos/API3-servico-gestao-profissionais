package br.com.chronos.profissionais.api.dto;

import java.io.Serializable;

public record ProjetoVinculadoResposta(
        int projetoId,
        String nomeProjeto,
        String codigoProjeto,
        double valorHora
) implements Serializable {
}