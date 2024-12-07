package com.evaluacion.kosmos.Repository;

import com.evaluacion.kosmos.Models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByConsultorioIdAndHorarioConsultaBetween(Long consultorioId, LocalDateTime start, LocalDateTime end);

    List<Cita> findByDoctorIdAndHorarioConsultaBetween(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<Cita> findByPacienteIdAndHorarioConsultaBetween(Long pacienteId, LocalDateTime start, LocalDateTime end);

    List<Cita> findByDoctorIdAndHorarioConsulta(LocalDateTime horarioConsulta, Long doctorId);

    List<Cita> findByConsultorioIdAndHorarioConsulta(LocalDateTime horarioConsulta, Long consultorioId);

    List<Cita> findByPacienteId(Long pacienteId);

    List<Cita> findByHorarioConsultaBetween(LocalDateTime start, LocalDateTime end);

}
