package com.webwaves.api.domain.consulta.validacoes;

import com.webwaves.api.domain.consulta.DadosAgendamentoConsulta;
import com.webwaves.api.domain.consulta.ValidacaoException;
import com.webwaves.api.domain.medicos.MedicoRepository;
import com.webwaves.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoAtivo implements InterfaceValidacao{
    @Autowired
    private MedicoRepository repository;
    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() == null){
            return;
        }

        var medicoAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoAtivo){
            throw new ValidacaoException("Medico não existe ou está inativo no sistema");
        }
    }
}
