package br.com.chronos.profissionais.api.dto;

import java.io.Serializable;
import java.util.List;

public record ProfissionalResposta(
        int id,
        String nome,
        String email,
        boolean ativo,
        int cargoId,
        List<ProjetoVinculadoResposta> projetos
) implements Serializable {
}