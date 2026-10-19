package com.clinica.recordatorios_citas_service.repository;

import com.clinica.recordatorios_citas_service.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByFechaHoraCitaBetweenAndRecordatorioEnviadoFalse(
            LocalDateTime desde, LocalDateTime hasta);
}