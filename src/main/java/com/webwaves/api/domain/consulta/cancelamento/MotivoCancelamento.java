package com.webwaves.api.domain.consulta.cancelamento;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.webwaves.api.domain.consulta.ValidacaoException;

public enum MotivoCancelamento {
    MEDICO_DESISTIU("Medico desistiu"),
    PACIENTE_DESISTIU("Paciente desistiu"),
    OUTRO_MOTIVO("Outros");

    private final String descricao;

    MotivoCancelamento(String descricao){
        this.descricao = descricao;
    }

    @JsonCreator
    public static MotivoCancelamento fromString(String value){
        for(MotivoCancelamento motivo : MotivoCancelamento.values()){
            if(motivo.descricao.equalsIgnoreCase(value)){
                return motivo;
            }
        }
         throw new ValidacaoException("Motivo informado inválido. (" + value + ")");
    }

}
