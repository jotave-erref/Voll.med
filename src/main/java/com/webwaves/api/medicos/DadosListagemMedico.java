package com.webwaves.api.medicos;

public record DadosListagemMedico(String nome, String email, String crm, EspecialidadeMedico especialidade) {

    public DadosListagemMedico(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
