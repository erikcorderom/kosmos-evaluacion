package com.evaluacion.kosmos.Models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
public class Paciente extends Persona {

    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;

    public Paciente() {}

    public Paciente(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String telefono, String email) {
        super(nombre, apellidoPaterno, apellidoMaterno);  // Llamar al constructor de la clase base Persona
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
    }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public void infoPaciente() {
        super.infoPersona();
        System.out.println("Fecha de Nacimiento: " + fechaNacimiento);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Email: " + email);
    }
}
