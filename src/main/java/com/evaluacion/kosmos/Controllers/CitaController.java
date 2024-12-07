package com.evaluacion.kosmos.Controllers;

import com.evaluacion.kosmos.Models.Cita;
import com.evaluacion.kosmos.Service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;


    @GetMapping("/alta")
    public String mostrarFormularioAlta(Model model) {
        model.addAttribute("cita", new Cita());
        return "altaCita";
    }

    @GetMapping
    public List<Cita> getAllCitas() {
        return citaService.getAllCitas();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return ResponseEntity.ok().build();
    }

    // Método para guardar la cita a través de un formulario
    @PostMapping("/agendar")
    public String agendarCita(@ModelAttribute Cita cita) {
        citaService.saveCita(cita);
        return "redirect:/citas/alta";
    }

    @GetMapping("/consultar/fecha")
    public List<Cita> consultarCitasPorFecha(@RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fin) {
        return citaService.consultarCitasPorFecha(inicio, fin);
    }

    @GetMapping("/consultar/consultorio")
    public List<Cita> consultarCitasPorConsultorio(@RequestParam Long consultorioId, @RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fin) {
        return citaService.consultarCitasPorConsultorio(consultorioId, inicio, fin);
    }

    @GetMapping("/consultar/doctor")
    public List<Cita> consultarCitasPorDoctor(@RequestParam Long doctorId, @RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fin) {
        return citaService.consultarCitasPorDoctor(doctorId, inicio, fin);
    }

    @DeleteMapping("/cancelar/{id}")
    public boolean cancelarCita(@PathVariable Long id) {
        return citaService.cancelarCita(id);
    }

    @PutMapping("/editar/{id}")
    public Cita editarCita(@PathVariable Long id, @RequestBody Cita cita) {
        return citaService.editarCita(id, cita);
    }
}
