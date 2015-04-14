package org.jcruells.sm.server.client;

import java.util.Collection;

import org.jcruells.sm.server.repository.Patient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import retrofit.http.GET;
import retrofit.http.POST;

/**
 * This interface defines an API for a VideoSvc. The
 * interface is used to provide a contract for client/server
 * interactions. The interface is annotated with Retrofit
 * annotations so that clients can automatically convert the
 * 
 * 
 * @author jules
 *
 */
public interface PatientSvcApi {
	
	public static final String PASSWORD_PARAMETER = "password";

	public static final String USERNAME_PARAMETER = "username";

	public static final String TOKEN_PATH = "/oauth/token";
	
	public static final String PATIENTS_SVC_PATH = "/patients";
	public static final String PATIENT_BY_ID_SVC_PATH = PATIENTS_SVC_PATH + "/{id}";
	
	@POST(PATIENTS_SVC_PATH)
	public Void updatePatient(@RequestBody Patient patient);
	
	@GET(PATIENT_BY_ID_SVC_PATH)
	public Patient getPatient(@PathVariable("id") int id);
	
	@GET(PATIENTS_SVC_PATH)
	public Collection <Patient> getUpdatedPatients(@RequestParam String fromTimestamp);
	
	
	
}
