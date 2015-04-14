/*
 * 
 */

package org.jcruells.sm.server;

import javax.servlet.http.HttpServletRequest;

import org.jcruells.sm.server.client.DoctorSvcApi;
import org.jcruells.sm.server.repository.Doctor;
import org.jcruells.sm.server.repository.DoctorRepository;
import org.jcruells.sm.server.repository.User;
import org.jcruells.sm.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@Controller
public class DoctorController implements DoctorSvcApi {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private DoctorRepository doctors;
	
	@Autowired
	private UserRepository users;
	

	/**
	 * This service is called from a patient client that wants to push 
	 * his new data to the server
	 */
	@Override
	@RequestMapping(value=DoctorSvcApi.DOCTORS_SVC_PATH, method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public Void updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		System.out.println("inside updatePatient");
		

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); 
	    User user = users.findByUsername(username);
	    final int userId = user.getRecordNumber();
		
		System.out.println("return patient: " + doctors.findByUserId(userId));
		
		Doctor d = doctors.findByUserId(userId);
		
		doctors.save(d);
		
		System.out.println("return");
		return null;
	}

	
	/**
	 * This service is called to get updated patient from the server
	 */
	@Override
	@RequestMapping(value=DoctorSvcApi.DOCTOR_BY_ID_SVC_PATH, method=RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public @ResponseBody Doctor getDoctor(@PathVariable("id") int id) {
		// TODO Auto-generated method stub
		System.out.println("inside getPatient: " + id);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); 
	    User user = users.findByUsername(username);
	    final int userId = user.getRecordNumber();
		
		System.out.println("return doctor with userid: " + userId);
		
		return doctors.findByUserId(id);
	}
	
	
	
	

	/* Status codes */
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No patient is found")  // 404
	public class PatientNotFoundException extends RuntimeException {
		
       private static final long serialVersionUID = 1L;
		public PatientNotFoundException() {
        	super("No patient is found");
        }
    }

	


}
