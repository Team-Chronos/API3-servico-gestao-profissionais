package br.com.chronos.profissionais.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProfissionalRequisicao(
        @NotBlank @Size(max = 256) String nome,
        @NotBlank @Email @Size(max = 256) String email,
        @NotBlank @Size(max = 255) String senhaHash,
        @NotNull Boolean ativo,
        @NotNull Integer cargoId
) {
}
