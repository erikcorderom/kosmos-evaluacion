package com.evaluacion.kosmos.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultorio_id")
    private Consultorio consultorio;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime horarioConsulta;


    public Cita(){

    }

    public Cita(LocalDateTime horarioConsulta, Paciente paciente, Consultorio consultorio, Doctor doctor) {
        this.horarioConsulta = horarioConsulta;
        this.paciente = paciente;
        this.consultorio = consultorio;
        this.doctor = doctor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Consultorio getConsultorio() { return consultorio; }
    public void setConsultorio(Consultorio consultorio) { this.consultorio = consultorio; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public LocalDateTime getHorarioConsulta() { return horarioConsulta; }
    public void setHorarioConsulta(LocalDateTime horarioConsulta) { this.horarioConsulta = horarioConsulta; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public void infoCitas(){
         System.out.println("Cita: " + horarioConsulta);
         paciente.infoPersona();
         consultorio.infoConsultorio();
         doctor.infoDoctor();

    }
}
