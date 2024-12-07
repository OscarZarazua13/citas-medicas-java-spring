/**
 * 
 */
package com.example.citas_medicas_app.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.citas_medicas_app.models.Cita;
import com.example.citas_medicas_app.models.Consultorio;
import com.example.citas_medicas_app.models.Doctor;
import com.example.citas_medicas_app.repository.ICitaRepository;
import com.example.citas_medicas_app.repository.IConsultorioRepository;
import com.example.citas_medicas_app.repository.IDoctorRepository;

/**
 * @author ozarazua
 *
 */
@Service
public class CitaService {
	
	@Autowired
	ICitaRepository citaRepository;
	
	@Autowired
	IConsultorioRepository consultorioRepository;
	
	@Autowired
	IDoctorRepository doctorRepository;

    public ResponseEntity<Object> crearCita(Integer consultorioId, Integer doctorId, String fecha, String nombrePaciente) {
    	Cita nuevaCita = new Cita();
    	Consultorio consultorio = consultorioRepository.findByConsultorioId(consultorioId);
    	Doctor doctor = doctorRepository.findByDoctorId(doctorId);
    	nuevaCita.setConsultorio(consultorio);
    	nuevaCita.setDoctor(doctor);
    	nuevaCita.setNombrePaciente(nombrePaciente);
    	nuevaCita.setHorarioConsulta((LocalDateTime.parse(fecha)));
        validarReglas(nuevaCita);
        citaRepository.save(nuevaCita);
        return new ResponseEntity<>("La cita se generó correctamente", HttpStatus.OK); 
    }

    private void validarReglas(Cita nuevaCita) {
        LocalDateTime inicio = nuevaCita.getHorarioConsulta();
        LocalDateTime fin = inicio.plusHours(1); // La duración de la cita es 1 hora.

        // 1. Consultorio a la misma hora
        if (!citaRepository.findByConsultorioIdAndHorarioConsultaBetween(
                nuevaCita.getConsultorio().getId(), inicio, fin).isEmpty()) {
            throw new IllegalArgumentException("El consultorio ya tiene una cita en ese horario.");
        }

        // 2. Doctor a la misma hora
        if (!citaRepository.findByDoctorIdAndHorarioConsultaBetween(
                nuevaCita.getDoctor().getId(), inicio, fin).isEmpty()) {
            throw new IllegalArgumentException("El doctor ya tiene una cita en ese horario.");
        }

        // 3. Paciente con menos de 2 horas de diferencia
        List<Cita> citasPaciente = citaRepository.findByNombrePacienteAndHorarioConsultaBetween(
                nuevaCita.getNombrePaciente(),
                inicio.toLocalDate().atStartOfDay(),
                inicio.toLocalDate().atTime(LocalTime.MAX)
        );
        for (Cita cita : citasPaciente) {
            if (Math.abs(cita.getHorarioConsulta().toLocalTime().until(inicio.toLocalTime(), java.time.temporal.ChronoUnit.HOURS)) < 2) {
                throw new IllegalArgumentException("El paciente ya tiene una cita con menos de 2 horas de diferencia.");
            }
        }

        // 4. Doctor con máximo 8 citas al día
        long citasDelDia = citaRepository.countByDoctorIdAndHorarioConsultaBetween(
                nuevaCita.getDoctor().getId(),
                inicio.toLocalDate().atStartOfDay(),
                inicio.toLocalDate().atTime(LocalTime.MAX)
        );
        if (citasDelDia >= 8) {
            throw new IllegalArgumentException("El doctor no puede tener más de 8 citas en el día.");
        }
    }
    
    public ResponseEntity<Object> consultarCitas(LocalDate fecha, Integer consultorioId, Integer doctorId) {
    	List<Cita> list = new ArrayList<>();
        if (fecha != null) {
        	list = citaRepository.findByHorarioConsultaBetween(
                fecha.atStartOfDay(), fecha.atTime(LocalTime.MAX)
            );
        } else if (consultorioId != null) {
        	list = citaRepository.findByConsultorioId(consultorioId);
        } else if (doctorId != null) {
        	list = citaRepository.findByDoctorId(doctorId);
        }
        
        list = (List<Cita>) citaRepository.findAll();
        
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    public ResponseEntity<Object> cancelarCita(Integer id) {
        Cita cita = citaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
        if (cita.getHorarioConsulta().isAfter(LocalDateTime.now())) {
            citaRepository.delete(cita);
            return new ResponseEntity<>("La cita se cancelo correctamente", HttpStatus.OK); 
        } else {
            throw new IllegalArgumentException("No se puede cancelar una cita ya realizada.");
        }
    }
    
    public ResponseEntity<Object>  editarCita(Integer id, Cita citaEditada) {
        Cita citaExistente = citaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
        if (citaExistente.getHorarioConsulta().isAfter(LocalDateTime.now())) {
            citaEditada.setId(id);
            validarReglas(citaEditada);
            citaRepository.save(citaEditada);
            return new ResponseEntity<>("La cita se editó correctamente", HttpStatus.OK); 
        } else {
            throw new IllegalArgumentException("No se puede editar una cita ya realizada.");
        }
    }
}
