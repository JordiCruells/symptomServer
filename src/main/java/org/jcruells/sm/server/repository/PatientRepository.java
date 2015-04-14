package org.jcruells.sm.server.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
	
	public Patient findByRecordId(int recordId);
	
	public ArrayList<Patient> findAllByDoctorId(long userId);
	
	public ArrayList<Patient> getAllByDoctorIdAndServerTimestampGreaterThan(long doctorId, String serverTimestamp);
	/*
	 * See: http://docs.spring.io/spring-data/jpa/docs/1.3.0.RELEASE/reference/html/jpa.repositories.html 
	 * for more examples of writing query methods
	 */
}

