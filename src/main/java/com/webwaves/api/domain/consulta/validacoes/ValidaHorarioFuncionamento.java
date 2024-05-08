package com.webwaves.api.domain.consulta.validacoes;

import com.webwaves.api.domain.consulta.DadosAgendamentoConsulta;
import com.webwaves.api.domain.consulta.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidaHorarioFuncionamento implements InterfaceValidacao{
    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var horarioConsulta = dados.data();
        var horarioAberturaClinica = horarioConsulta.getHour() < 7;
        var horarioFechamentoClinica = horarioConsulta.getHour() > 18;
        var diaConsulta = horarioConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        if(horarioAberturaClinica || horarioFechamentoClinica || diaConsulta){
            throw new ValidacaoException("Agendamento de consultas disponível entre 7:00 horas às 18:00 horas de segunda a sábado");
        }
    }
}
