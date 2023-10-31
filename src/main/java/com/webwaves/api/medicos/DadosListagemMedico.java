package com.webwaves.api.medicos;

public record DadosListagemMedico(Long id, String nome, String email, String crm, EspecialidadeMedico especialidade) {

    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
