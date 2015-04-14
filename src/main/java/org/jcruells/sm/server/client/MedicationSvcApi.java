package org.jcruells.sm.server.client;

import java.util.Collection;

import org.jcruells.sm.server.repository.PatientMedication;
import org.springframework.web.bind.annotation.RequestParam;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface MedicationSvcApi {
	
	public static final String PASSWORD_PARAMETER = "password";

	public static final String USERNAME_PARAMETER = "username";

	public static final String TOKEN_PATH = "/oauth/token";
	
	public static final String MEDICATIONS_SVC_PATH = "/medications";
	
	
	@POST(MEDICATIONS_SVC_PATH)
	public Void updatePatientMedications(@Body Collection<PatientMedication> patientMedications);
	
	@GET(MEDICATIONS_SVC_PATH)
	public Collection<PatientMedication> getNewMedications(@RequestParam String fromTimestamp);
	
}
