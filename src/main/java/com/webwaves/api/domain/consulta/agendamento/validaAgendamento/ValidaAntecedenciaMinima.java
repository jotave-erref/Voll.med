package com.webwaves.api.domain.consulta.agendamento.validaAgendamento;

import com.webwaves.api.domain.consulta.agendamento.DadosAgendamentoConsulta;
import com.webwaves.api.domain.consulta.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidaAntecedenciaMinima implements InterfaceValidacao {
    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var horarioConsulta = dados.data();
        var horaAtual = LocalDateTime.now();
        var validaAntecedencia = Duration.between(horaAtual, horarioConsulta).toMinutes();
        if(validaAntecedencia < 30){
            throw new ValidacaoException("Consutas deve ser agendadas com antecedencia minima de 30 minutos");
        }
    }
}
