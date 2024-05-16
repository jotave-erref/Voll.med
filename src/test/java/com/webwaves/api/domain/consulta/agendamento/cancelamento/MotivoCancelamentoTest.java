package com.webwaves.api.domain.consulta.agendamento.cancelamento;

import com.webwaves.api.domain.consulta.Consulta;
import com.webwaves.api.domain.consulta.ValidacaoException;
import com.webwaves.api.domain.consulta.cancelamento.DadosCancelamentoConsulta;
import com.webwaves.api.domain.consulta.cancelamento.MotivoCancelamento;
import com.webwaves.api.domain.consulta.cancelamento.RegraDeNeogcioCancelamento;
import com.webwaves.api.domain.consulta.cancelamento.validaCancelamento.ValidaMotivoCancelamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MotivoCancelamentoTest {

    @Test
    public void shouldTransformFromString(){
    var motivo = MotivoCancelamento.fromString("Outros");
        Assertions.assertEquals(motivo, MotivoCancelamento.OUTRO_MOTIVO);
    }

}
