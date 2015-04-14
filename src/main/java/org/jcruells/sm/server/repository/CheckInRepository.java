package org.jcruells.sm.server.repository;


import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepository extends CrudRepository<CheckIn, Long> {

	public ArrayList<CheckIn> getAllByPatientRecordIdAndServerTimestampGreaterThan(int patientRecordId, String serverTimestamp);
}



