/**
 * 
 */
package com.example.citas_medicas_app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ozarazua
 *
 */
@Entity
@Table(name = "doctor")
public class Doctor {
	@Id
	@Column(name = "doctor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;
	
	@Column(name = "nombre")
    private String nombre;
	@Column(name = "apellido_paterno")
    private String apellidoPaterno;
	@Column(name = "apellido_materno")
    private String apellidoMaterno;
	@Column(name = "especialidad")
    private String especialidad;

	public Integer getId() {
		return doctorId;
	}

	public void setId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
