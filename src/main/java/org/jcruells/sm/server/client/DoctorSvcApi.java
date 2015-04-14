package org.jcruells.sm.server.client;

import org.jcruells.sm.server.repository.Doctor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import retrofit.http.GET;
import retrofit.http.POST;


public interface DoctorSvcApi {
	
	public static final String PASSWORD_PARAMETER = "password";

	public static final String USERNAME_PARAMETER = "username";

	public static final String TOKEN_PATH = "/oauth/token";
	
	public static final String DOCTORS_SVC_PATH = "/doctors";
	public static final String DOCTOR_BY_ID_SVC_PATH = DOCTORS_SVC_PATH + "/{id}";
	
	
	@POST(DOCTORS_SVC_PATH)
	public Void updateDoctor(@RequestBody Doctor doctor);
	
	@GET(DOCTOR_BY_ID_SVC_PATH)
	public Doctor getDoctor(@PathVariable("id") int id );
	
	
	
	
	
	
}
