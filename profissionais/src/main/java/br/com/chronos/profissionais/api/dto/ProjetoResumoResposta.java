package br.com.chronos.profissionais.api.dto;

import java.io.Serializable;

public record ProjetoResumoResposta(
        int id,
        String nome,
        String codigo,
        double valorHoraBase
) implements Serializable {
}