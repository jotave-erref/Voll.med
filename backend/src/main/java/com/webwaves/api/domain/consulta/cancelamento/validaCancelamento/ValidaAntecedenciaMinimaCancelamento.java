package com.webwaves.api.domain.consulta.cancelamento.validaCancelamento;

import com.webwaves.api.domain.consulta.ConsultaRepository;
import com.webwaves.api.domain.consulta.ValidacaoException;
import com.webwaves.api.domain.consulta.cancelamento.DadosCancelamentoConsulta;
import com.webwaves.api.domain.consulta.cancelamento.InterfaceValidaCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidaAntecedenciaMinimaCancelamento implements InterfaceValidaCancelamento {
    @Autowired
    private ConsultaRepository repository;
    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consultaCancelada = repository.getReferenceById(dados.consultaId());
        var horaAtual = LocalDateTime.now();
        var antecedenciaCancelamento = Duration.between(consultaCancelada.getData(), horaAtual).toHours();
        if(antecedenciaCancelamento > 24 ){
            throw new ValidacaoException("Cancelamento de consultas deve ser realizados com antecedência minima de 24 horas do horário da consulta");
        }
    }
}
