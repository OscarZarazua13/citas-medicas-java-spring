/**
 * 
 */
package com.example.citas_medicas_app.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author ozarazua
 *
 */
@Entity
@Table(name = "consultorio")
public class Consultorio {
	@Id
	@Column(name = "consultorio_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consultorioId;

	@Column(name = "numero")
    private Integer numero;
	@Column(name = "piso")
    private Integer piso;

    @OneToMany
    private List<Cita> citas;

	public Integer getId() {
		return consultorioId;
	}

	public void setId(Integer consultorioId) {
		this.consultorioId = consultorioId;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}
}