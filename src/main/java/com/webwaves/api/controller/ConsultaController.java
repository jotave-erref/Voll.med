package com.webwaves.api.controller;

import com.webwaves.api.domain.consulta.agendamento.DadosAgendamentoConsulta;
import com.webwaves.api.domain.consulta.cancelamento.DadosCancelamentoConsulta;
import com.webwaves.api.domain.consulta.agendamento.RegraDeNegocioConsulta;
import com.webwaves.api.domain.consulta.cancelamento.RegraDeNeogcioCancelamento;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private RegraDeNegocioConsulta regraAgendamento;
    @Autowired
    private RegraDeNeogcioCancelamento regraCancelamento;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody DadosAgendamentoConsulta dados){
        var detalheConsulta = regraAgendamento.validaAgendamento(dados);
        return ResponseEntity.ok(detalheConsulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
        System.out.println(dados.motivo());
        regraCancelamento.validaCancelamentoConsulta(dados);
        return ResponseEntity.noContent().build();
    }
}
