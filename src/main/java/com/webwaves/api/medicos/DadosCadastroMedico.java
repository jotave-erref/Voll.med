package com.webwaves.api.medicos;

import com.webwaves.api.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, EspecialidadeMedico especialidade, DadosEndereco endereco) {
}
