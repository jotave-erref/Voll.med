package com.webwaves.api.domain.consulta;

import com.webwaves.api.domain.medicos.EspecialidadeMedico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        Long  idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime data,
        EspecialidadeMedico especialidade) {
}
