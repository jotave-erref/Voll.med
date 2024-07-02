package com.webwaves.api.controller;

import com.webwaves.api.domain.consulta.agendamento.DadosAgendamentoConsulta;
import com.webwaves.api.domain.consulta.agendamento.DadosDetalhamentoConsulta;
import com.webwaves.api.domain.consulta.agendamento.RegraDeNegocioConsulta;
import com.webwaves.api.domain.medicos.EspecialidadeMedico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {
    @Autowired
    private MockMvc mvc; //Para criar requisições

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson; //Simula o json de entrada

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson; //Simula o json de saída

    @MockBean
    private RegraDeNegocioConsulta regraDeNegocioConsulta;

    @Test
    @DisplayName("Deve retorrnar código 400 quando informações inválidas")
    @WithMockUser
    void agendarCenario1() throws Exception {
        var response = mvc.perform(post("/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retorrnar código 200 quando informações inválidas")
    @WithMockUser
    void agendarCenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = EspecialidadeMedico.DERMATOLOGIA;
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2L, 2L, data);

        when(regraDeNegocioConsulta.validaAgendamento(any())).thenReturn(dadosDetalhamento);

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON) //Cabeçalho contentType com o valor do tipo Json
                        .content(dadosAgendamentoConsultaJson.write(
                                new DadosAgendamentoConsulta(2L, 2L, data, especialidade)
                        ).getJson())) //JaksonTester transforma o Record passado como parâmetro em uma String Json
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJson.write(dadosDetalhamento).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}