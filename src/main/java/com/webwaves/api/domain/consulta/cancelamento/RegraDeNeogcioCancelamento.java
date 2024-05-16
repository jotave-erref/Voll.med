package com.webwaves.api.domain.consulta.cancelamento;

import com.webwaves.api.domain.consulta.ConsultaRepository;
import com.webwaves.api.domain.consulta.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegraDeNeogcioCancelamento {
    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private List<InterfaceValidaCancelamento> validaCancelamentoList = new ArrayList<>();

    public void validaCancelamentoConsulta(DadosCancelamentoConsulta dados) {
        if (!repository.existsById(dados.consultaId())) {
            throw new ValidacaoException("Id da consulta inválido. Informe um id existente");
        }

        validaCancelamentoList.forEach(c -> c.validar(dados));
        var consultaCancelada = repository.getReferenceById(dados.consultaId());
        var motivo = MotivoCancelamento.fromString(dados.motivo());
        consultaCancelada.cancelar(motivo);

    }
}
