package com.evaluacion.kosmos.Service;


import com.evaluacion.kosmos.Repository.ConsultorioRepository;
import com.evaluacion.kosmos.Models.Consultorio;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ConsultorioService {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    // Obtener todos los consultorios
    public List<Consultorio> getAllConsultorios() {
        return consultorioRepository.findAll();
    }

    // Guardar un consultorio
    public Consultorio saveConsultorio(Consultorio consultorio) {
        return consultorioRepository.save(consultorio);
    }

    // Buscar un consultorio por ID
    public Optional<Consultorio> getConsultorioById(Long id) {
        return consultorioRepository.findById(id);
    }

    // Eliminar un consultorio por ID
    public void deleteConsultorio(Long id) {
        consultorioRepository.deleteById(id);
    }
}
