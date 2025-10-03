package com.webwaves.api.domain.consulta.agendamento.validaAgendamento;

import com.webwaves.api.domain.consulta.ConsultaRepository;
import com.webwaves.api.domain.consulta.agendamento.DadosAgendamentoConsulta;
import com.webwaves.api.domain.consulta.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaConsultaMedicoDataEHora implements InterfaceValidacao{
    @Autowired
    private ConsultaRepository repository;
    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var horaConsulta = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if(horaConsulta){
            throw new ValidacaoException("Médico indisponível na data e hora selecionada.");
        }
    }
}
