package com.webwaves.api.domain.consulta.cancelamento.validaCancelamento;

import com.webwaves.api.domain.consulta.ValidacaoException;
import com.webwaves.api.domain.consulta.cancelamento.DadosCancelamentoConsulta;
import com.webwaves.api.domain.consulta.cancelamento.InterfaceValidaCancelamento;
import org.springframework.stereotype.Component;

@Component
public class ValidaMotivoCancelamento implements InterfaceValidaCancelamento {
    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        if(dados.motivo() == null){
            throw new ValidacaoException("Motivo do cancelamento deve ser informado");
        }
    }
}
