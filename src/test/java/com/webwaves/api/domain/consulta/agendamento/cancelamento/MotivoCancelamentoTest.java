package com.webwaves.api.domain.consulta.agendamento.cancelamento;

import com.webwaves.api.domain.consulta.cancelamento.MotivoCancelamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MotivoCancelamentoTest {

    @Test
    public void shouldTransformFromString(){
    var motivo = MotivoCancelamento.fromString("Outros");
        Assertions.assertEquals(motivo, MotivoCancelamento.OUTRO_MOTIVO);
    }

}
