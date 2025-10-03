package com.webwaves.api.domain.medicos;

import com.webwaves.api.domain.consulta.Consulta;
import com.webwaves.api.domain.endereco.DadosEndereco;
import com.webwaves.api.domain.paciente.DadosCadastroPaciente;
import com.webwaves.api.domain.paciente.Paciente;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("Deve retornar null quando médico buscado já estiver com consulta marcada na data e hora")
    void buscaMedicoAleatorioCenario1(){
        var segundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("xxxx", "xxxxx", "857548", EspecialidadeMedico.CARDIOLOGIA);
        var paciente = cadastrarPaciente("xxxxx", "zzzzzz", "85498565898");
        cadastrarConsulta(paciente, medico, segundaAs10);
        var medicoBuscado = medicoRepository.buscaMedicoAleatorio(EspecialidadeMedico.CARDIOLOGIA, segundaAs10);

        assertThat(medicoBuscado).isNull();
    }

    @Test
    @DisplayName("Deve retornar um médico disponível na data e hora da consulta ")
    void buscaMedicoAleatorioCenario2(){
        var segundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("xxxx", "xxxxx", "857548", EspecialidadeMedico.CARDIOLOGIA);
//        var paciente = cadastrarPaciente("xxxxx", "zzzzzz", "85498565898");
//        cadastrarConsulta(paciente, medico, segundaAs10);
        var medicoBuscado = medicoRepository.buscaMedicoAleatorio(EspecialidadeMedico.CARDIOLOGIA, segundaAs10);

        assertThat(medicoBuscado).isEqualTo(medico);
    }

    private void cadastrarConsulta(Paciente paciente, Medico medico, LocalDateTime data){

        em.persist(new Consulta(paciente, medico, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, EspecialidadeMedico especialidade){
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf){
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf){
        return new DadosCadastroPaciente(
                nome,
                email,
                "48988084444",
                cpf,
                dadosEndereco()
                );
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, EspecialidadeMedico especialidade){
         return new DadosCadastroMedico(
                 nome,
                 email,
                 crm,
             "585468788",
                 EspecialidadeMedico.CARDIOLOGIA,
                 dadosEndereco()
         );
    }

    private DadosEndereco dadosEndereco(){
        return new DadosEndereco("Rua xxx", "xxx", "88888888", "xxxxx", "ss", "333333", "zzzzz" );
    }
}