package com.webwaves.api.controller;

import com.webwaves.api.domain.consulta.DadosAgendamentoConsulta;
import com.webwaves.api.domain.consulta.RegraDeNegocioConsulta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private RegraDeNegocioConsulta regraDeNegocio;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody DadosAgendamentoConsulta dados){
        var detalheConsulta = regraDeNegocio.validaAgendamento(dados);
        return ResponseEntity.ok(detalheConsulta);
    }
}
