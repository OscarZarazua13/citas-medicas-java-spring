/**
 * 
 */
package com.example.citas_medicas_app.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.citas_medicas_app.models.Consultorio;

/**
 * @author ozarazua
 *
 */
public interface IConsultorioRepository extends CrudRepository<Consultorio, Integer>{
	Consultorio findByConsultorioId(Integer consultorioId);
}
