package org.jcruells.sm.server.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientMedicationRepository extends CrudRepository<PatientMedication, Long> {

	
	public ArrayList<PatientMedication> findByPatientRecordIdAndServerTimestampGreaterThan(int patientRecordId, String serverTimestamp );
	
	public PatientMedication findByPatientRecordIdAndPatientMedicationId(int patientRecordId, int patientMedicationId);
	/*
	 * See: http://docs.spring.io/spring-data/jpa/docs/1.3.0.RELEASE/reference/html/jpa.repositories.html 
	 * for more examples of writing query methods
	 */
	
	
}

