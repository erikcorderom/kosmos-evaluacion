package com.evaluacion.kosmos.Models;

import javax.persistence.*;

@Entity
@Table(name = "consultorios")
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroConsultorio;
    private int piso;

    public Consultorio(){

    }

    public Consultorio(String numeroConsultorio, int piso) {
        this.numeroConsultorio = numeroConsultorio;
        this.piso = piso;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroConsultorio() { return numeroConsultorio; }
    public void setNumeroConsultorio(String numeroConsultorio) { this.numeroConsultorio = numeroConsultorio; }

    public int getPiso() { return piso; }
    public void setPiso(int piso) { this.piso = piso; }

    public void infoConsultorio() {
        System.out.println("Consultorio: " + numeroConsultorio +
                "\nPiso: " + piso);
    }
}
