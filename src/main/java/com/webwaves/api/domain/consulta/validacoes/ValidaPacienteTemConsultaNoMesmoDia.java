package com.webwaves.api.domain.consulta.validacoes;

import com.webwaves.api.domain.consulta.ConsultaRepository;
import com.webwaves.api.domain.consulta.DadosAgendamentoConsulta;
import com.webwaves.api.domain.consulta.ValidacaoException;
import com.webwaves.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteTemConsultaNoMesmoDia implements InterfaceValidacao{
    @Autowired
    private ConsultaRepository repository;
    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacienteConsultaNoMesmoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacienteConsultaNoMesmoDia){
            throw new ValidacaoException("Paciente já possui consulta marcada no mesmo dia");
        }
    }
}
