package com.evaluacion.kosmos.Models;


import javax.persistence.*;

@Entity
@Table(name = "pacientes")
public class Paciente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Paciente() {}

    public Paciente(String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.setNombre(nombre);
        this.setApellidoPaterno(apellidoPaterno);
        this.setApellidoMaterno(apellidoMaterno);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public void infoDoctor() {
        super.infoPersona();
    }
}
