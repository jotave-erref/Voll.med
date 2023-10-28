package com.webwaves.api.medicos;

import com.webwaves.api.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
