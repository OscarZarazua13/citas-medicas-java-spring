/**
 * 
 */
package com.example.citas_medicas_app.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.citas_medicas_app.models.Cita;
import com.example.citas_medicas_app.services.CitaService;

/**
 * @author ozarazua
 *
 */
@RestController
@RequestMapping("/app/citas")
public class CitaController {
	
	@Autowired
    private CitaService citaService;


    @PostMapping
    public ResponseEntity<Object> crearCita(@RequestParam Integer consultorioId,
    	    @RequestParam Integer doctorId,
    	    @RequestParam String fecha,
    	    @RequestParam String nombrePaciente) {
        return citaService.crearCita(consultorioId, doctorId, fecha, nombrePaciente);
    }

    @GetMapping
    public ResponseEntity<Object> consultarCitas(@RequestParam(required = true) LocalDate fecha,
                                     @RequestParam(required = true) Integer consultorioId,
                                     @RequestParam(required = true) Integer doctorId) {
        return citaService.consultarCitas(fecha, consultorioId, doctorId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> cancelarCita(@PathVariable(required = true) Integer id) {
        return citaService.cancelarCita(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editarCita(@PathVariable(required = true) Integer id, @RequestBody(required = true) Cita citaEditada) {
        return citaService.editarCita(id, citaEditada);
    }
}

