package com.evaluacion.kosmos.Models;


import javax.persistence.*;

@Entity
@Table(name = "doctores")
public class Doctor extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String especialidad;

    public Doctor() {}

    public Doctor(String nombre, String apellidoPaterno, String apellidoMaterno, String especialidad) {
        this.setNombre(nombre);
        this.setApellidoPaterno(apellidoPaterno);
        this.setApellidoMaterno(apellidoMaterno);
        this.especialidad = especialidad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public void infoDoctor() {
        super.infoPersona();
        System.out.println("Especialidad: " + especialidad);
    }
}
