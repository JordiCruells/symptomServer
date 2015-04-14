package org.jcruells.sm.server.client;

import java.util.Collection;

import org.jcruells.sm.server.repository.CheckIn;
import org.springframework.web.bind.annotation.RequestParam;

import retrofit.http.Body;
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
public interface CheckInSvcApi {
	
	public static final String PASSWORD_PARAMETER = "password";

	public static final String USERNAME_PARAMETER = "username";

	public static final String TOKEN_PATH = "/oauth/token";
	
	
	// The path where we expect the check-ins service to live
	public static final String CHECKINS_SVC_PATH = "/checkins";
	
	
	@POST(CHECKINS_SVC_PATH)
	public Void addNewCheckIns(@Body Collection<CheckIn> checkins);
	
	@GET(CHECKINS_SVC_PATH)
	public Collection<CheckIn> getNewCheckIns(@RequestParam String fromTimestamp);
	
	
	
}
