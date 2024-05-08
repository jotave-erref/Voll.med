package com.webwaves.api.domain.consulta.validacoes;

import com.webwaves.api.domain.consulta.DadosAgendamentoConsulta;
import com.webwaves.api.domain.consulta.ValidacaoException;
import com.webwaves.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidaPacienteAtivo implements InterfaceValidacao{
    @Autowired
    private PacienteRepository repository;
    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteAtivo){
            throw new ValidacaoException("Paciente não existe ou está inativo no sistema");
        }
    }
}
