package com.webwaves.api.domain.consulta.agendamento;

import com.webwaves.api.domain.consulta.Consulta;
import com.webwaves.api.domain.consulta.ConsultaRepository;
import com.webwaves.api.domain.consulta.ValidacaoException;
import com.webwaves.api.domain.consulta.agendamento.validaAgendamento.InterfaceValidacao;
import com.webwaves.api.domain.medicos.Medico;
import com.webwaves.api.domain.medicos.MedicoRepository;
import com.webwaves.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegraDeNegocioConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<InterfaceValidacao> validacoes = new ArrayList<>();

    public DadosDetalhamentoConsulta validaAgendamento(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente inválido");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico não informado ou inválido");
        }

        validacoes.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = buscaMedico(dados);
        var consulta = new Consulta(paciente, medico, dados.data());
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico buscaMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidacaoException("Erro ao inserir os dados. Escolha a especialidade do médico");
        }
        return medicoRepository.buscaMedicoAleatorio(dados.especialidade(), dados.data());
    }
}
