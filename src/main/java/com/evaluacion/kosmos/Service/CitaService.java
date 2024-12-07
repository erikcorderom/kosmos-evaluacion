package com.evaluacion.kosmos.Service;

import com.evaluacion.kosmos.Repository.CitaRepository;
import com.evaluacion.kosmos.Models.Cita;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    // Obtener todas las citas
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    // Guardar una cita
    public Cita saveCita(Cita cita) {
        //log.info("Validando información antes de proceder a guardar la cita");
        validarCita(cita);
        //log.info("Cita guardada exitosamente!");
        return citaRepository.save(cita);
    }

    // Buscar una cita por ID
    public Optional<Cita> getCitaById(Long id) {
        return citaRepository.findById(id);
    }

    // Eliminar una cita por ID
    public void deleteCita(Long id) {
        citaRepository.deleteById(id);
    }


    // Validación: No se puede agendar cita en un mismo consultorio a la misma hora
    public boolean validaConsultorioDisponible(Long consultorioId, LocalDateTime fechaHora) {
        //log.info("Agendando cita en consultorio: " + consultorioId);
        List<Cita> citas = citaRepository.findByConsultorioIdAndHorarioConsulta(fechaHora, consultorioId);
        //log.info("Consultorio y horario disponible: " + citas.isEmpty());
        return citas.isEmpty();
    }

    // Validación: No se puede agendar cita para el mismo doctor a la misma hora
    public boolean validaDoctorDisponible(Long doctorId, LocalDateTime fechaHora) {
        //log.info("Agengando cita con el doctor: " + doctorId);
        List<Cita> citas = citaRepository.findByDoctorIdAndHorarioConsulta(fechaHora, doctorId);
        //log.info("Doctor ocupado en el horario seleccionado: " + citas.isEmpty());
        return citas.isEmpty();
    }

    // Validación: No se puede agendar cita para el mismo paciente en la misma hora ni con menos de 2 horas de diferencia
    public boolean validaPacienteDisponible(Long pacienteId, LocalDateTime fechaHora) {
        //log.info("Agengando cita al paciente: " + pacienteId);
        List<Cita> citas = citaRepository.findByPacienteIdAndHorarioConsultaBetween(pacienteId,
                fechaHora.minusDays(1), fechaHora.plusDays(1));

        if (!citas.isEmpty()) {
          //  log.info("Cantidad de citas del paciente: " + pacienteId + ", Total de Citas: " + citas.size());
            for (Cita cita : citas) {
                // Si ya tiene una cita en el mismo día
                if (cita.getHorarioConsulta().toLocalDate().equals(fechaHora.toLocalDate())) {
            //        log.info("Paciente: " + cita.getPaciente().getNombre() + " ya cuenta con una cita en este horario seleccionado " +
              //              cita.getHorarioConsulta().toLocalDate() + " o con una diferencia de 2 horas. Favor de seleccionar otro horario.");
                    return false; // Ya tiene cita
                }
            }
        }

        return true;
    }

    // Validación: Un mismo doctor no puede tener más de 8 citas en el día
    public boolean validaLimiteCitasDoctor(Long doctorId, LocalDateTime fechaHora) {
        //log.info("Limite de Citas por Doctor: " + doctorId);
        LocalDateTime inicioDelDia = fechaHora.toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = inicioDelDia.plusDays(1).minusSeconds(1);
        List<Cita> citasDelDia = citaRepository.findByDoctorIdAndHorarioConsultaBetween(doctorId, inicioDelDia, finDelDia);

        boolean excedioLimite = citasDelDia.size() < 8;
        //log.info("Citas del Dia: " + citasDelDia.size() + (excedioLimite ? ". No se pueden tener mas de 8 citas al dia por Doctor." : ""));
        return excedioLimite;
    }

    // Método para consultar citas por fecha
    public List<Cita> consultarCitasPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        //log.info("Consultando citas por fecha");
        return citaRepository.findByHorarioConsultaBetween(inicio, fin);
    }

    // Método para consultar citas por consultorio
    public List<Cita> consultarCitasPorConsultorio(Long consultorioId, LocalDateTime inicio, LocalDateTime fin) {
        //log.info("Consultando citas por consultorio");
        return citaRepository.findByConsultorioIdAndHorarioConsultaBetween(consultorioId, inicio, fin);
    }

    // Método para consultar citas por doctor
    public List<Cita> consultarCitasPorDoctor(Long doctorId, LocalDateTime inicio, LocalDateTime fin) {
        //log.info("Consultando citas por doctor");
        return citaRepository.findByDoctorIdAndHorarioConsultaBetween(doctorId, inicio, fin);
    }

    public boolean cancelarCita(Long citaId) {
        //log.info("Cancelando cita: " + citaId);
        Cita cita = citaRepository.findById(citaId).orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if (cita.getHorarioConsulta().isBefore(LocalDateTime.now())) {
          //  log.info("No se puede cancelar una cita ya pasada");
            throw new RuntimeException("No se puede cancelar una cita ya pasada.");
        }
        //log.info("Cita cancelada");
        citaRepository.delete(cita);
        return true;
    }

    public Cita editarCita(Long citaId, Cita citaActualizada) {
        //log.info("Actualizando cita: " + citaId);
        Cita citaExistente = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        validarCita(citaActualizada);
        citaExistente.setDoctor(citaActualizada.getDoctor());
        citaExistente.setConsultorio(citaActualizada.getConsultorio());
        citaExistente.setPaciente(citaActualizada.getPaciente());
        citaExistente.setHorarioConsulta(citaActualizada.getHorarioConsulta());
        //log.info("Cita Actualizada");
        return citaRepository.save(citaExistente);
    }

    private void validarCita(Cita cita){
        // Validar que la nueva cita no tenga conflictos con las reglas de alta
        if (!validaConsultorioDisponible(cita.getConsultorio().getId(), cita.getHorarioConsulta() )) {
            throw new RuntimeException("El consultorio ya está ocupado en ese horario.");
        }
        if (!validaDoctorDisponible(cita.getDoctor().getId(), cita.getHorarioConsulta())) {
            throw new RuntimeException("El doctor ya tiene una cita en ese horario.");
        }
        if (!validaPacienteDisponible(cita.getPaciente().getId(), cita.getHorarioConsulta())) {
            throw new RuntimeException("El paciente ya tiene una cita en ese horario o no puede agendar otra cita tan cerca.");
        }
        if (!validaLimiteCitasDoctor(cita.getDoctor().getId(), cita.getHorarioConsulta())) {
            throw new RuntimeException("El doctor no puede tener más de 8 citas en un día.");
        }
    }

}
