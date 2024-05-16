package com.webwaves.api.domain.consulta;

import com.webwaves.api.domain.consulta.cancelamento.MotivoCancelamento;
import com.webwaves.api.domain.medicos.EspecialidadeMedico;
import com.webwaves.api.domain.medicos.Medico;
import com.webwaves.api.domain.paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "Consulta")
@Table(name = "consultas")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class    Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    @Column(name = "motivo_cancelamento")
    private MotivoCancelamento motivoCancelamento;

    public Consulta(Paciente paciente, Medico medico, LocalDateTime data){
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
    }

    public void cancelar(MotivoCancelamento motivo){
        this.motivoCancelamento = motivo;

    }
}
