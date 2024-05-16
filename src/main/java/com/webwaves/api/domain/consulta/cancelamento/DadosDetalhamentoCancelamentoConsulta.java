package com.webwaves.api.domain.consulta.cancelamento;

import com.webwaves.api.domain.consulta.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoCancelamentoConsulta(Long idMedico, Long idPaciente, LocalDateTime data, MotivoCancelamento motivo) {
    public DadosDetalhamentoCancelamentoConsulta(Consulta consulta){
        this(consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(), consulta.getMotivoCancelamento());
    }
}
