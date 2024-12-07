/**
 * 
 */
package com.example.citas_medicas_app.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ozarazua
 *
 */
@Entity
@Table(name = "cita")
public class Cita {
	@Id
	@Column(name = "cita_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Doctor doctorId;
    
    @ManyToOne
    private Consultorio consultorioId;
    
    @Column(name = "horario_consulta")
    private LocalDateTime horarioConsulta;
    @Column(name = "nombre_paciente")
    private String nombrePaciente;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Doctor getDoctor() {
		return doctorId;
	}
	public void setDoctor(Doctor doctorId) {
		this.doctorId = doctorId;
	}
	public Consultorio getConsultorio() {
		return consultorioId;
	}
	public void setConsultorio(Consultorio consultorioId) {
		this.consultorioId = consultorioId;
	}
	public LocalDateTime getHorarioConsulta() {
		return horarioConsulta;
	}
	public void setHorarioConsulta(LocalDateTime horarioConsulta) {
		this.horarioConsulta = horarioConsulta;
	}
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
}
