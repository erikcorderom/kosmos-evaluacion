package com.evaluacion.kosmos.Service;



import com.evaluacion.kosmos.Models.Doctor;
import com.evaluacion.kosmos.Repository.DoctorRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Obtener todos los doctores
    public List<Doctor> getAllDoctores() {
        return doctorRepository.findAll();
    }

    // Guardar un doctor
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Buscar un doctor por ID
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Eliminar un doctor por ID
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }


}
