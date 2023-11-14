package com.webwaves.api.domain.medicos;

import com.webwaves.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
                                  ) {
}
