/**
 * 
 */
package com.example.citas_medicas_app.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.citas_medicas_app.models.Doctor;

/**
 * @author ozarazua
 *
 */
public interface IDoctorRepository extends CrudRepository<Doctor, Integer>{
	Doctor findByDoctorId(Integer doctorId);
}
