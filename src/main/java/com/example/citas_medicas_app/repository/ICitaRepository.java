/**
 * 
 */
package com.example.citas_medicas_app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.citas_medicas_app.models.Cita;

/**
 * @author ozarazua
 *
 */
public interface ICitaRepository extends CrudRepository<Cita, Integer>{
	
	List<Cita> findByConsultorioIdAndHorarioConsultaBetween(Integer consultorioId, LocalDateTime start, LocalDateTime end);
	
    List<Cita> findByDoctorIdAndHorarioConsultaBetween(Integer doctorId, LocalDateTime start, LocalDateTime end);
    
    List<Cita> findByNombrePacienteAndHorarioConsultaBetween(String nombrePaciente, LocalDateTime start, LocalDateTime end);
    
    long countByDoctorIdAndHorarioConsultaBetween(Integer doctorId, LocalDateTime start, LocalDateTime end);
    
    List<Cita> findByHorarioConsultaBetween(LocalDateTime start, LocalDateTime end);

    List<Cita> findByConsultorioId(Integer consultorioId);

    List<Cita> findByDoctorId(Integer doctorId);

}
