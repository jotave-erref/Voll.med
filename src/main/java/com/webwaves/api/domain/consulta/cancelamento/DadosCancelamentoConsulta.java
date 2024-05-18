package com.webwaves.api.domain.consulta.cancelamento;

import com.webwaves.api.domain.consulta.cancelamento.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Long consultaId,
        @NotNull
        MotivoCancelamento motivo) {
}
