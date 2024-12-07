package com.evaluacion.kosmos.Models;

import javax.persistence.*;

@Entity
@Table(name = "doctores")
public class Doctor extends Persona {

    private String especialidad;

    public Doctor() {}

    public Doctor(String nombre, String apellidoPaterno, String apellidoMaterno, String especialidad) {
        super(nombre, apellidoPaterno, apellidoMaterno);  // Llamar al constructor de la clase base Persona
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public void infoDoctor() {
        super.infoPersona();
        System.out.println("Especialidad: " + especialidad);
    }
}
