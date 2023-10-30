package com.webwaves.api.controller;

import com.webwaves.api.medicos.DadosListagemMedico;
import com.webwaves.api.paciente.DadosCadastroPaciente;
import com.webwaves.api.paciente.DadosListagemPaciente;
import com.webwaves.api.paciente.Paciente;
import com.webwaves.api.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente paciente){
        repository.save(new Paciente(paciente));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listagemPaciente(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
}
