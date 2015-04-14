/*
 * 
 */

package org.jcruells.sm.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.jcruells.sm.server.client.PatientSvcApi;
import org.jcruells.sm.server.repository.CheckIn;
import org.jcruells.sm.server.repository.Doctor;
import org.jcruells.sm.server.repository.DoctorRepository;
import org.jcruells.sm.server.repository.Patient;
import org.jcruells.sm.server.repository.PatientRepository;
import org.jcruells.sm.server.repository.User;
import org.jcruells.sm.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@Controller
public class PatientController implements PatientSvcApi {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private PatientRepository patients;
	
	@Autowired
	private UserRepository users;
	
	@Autowired
	private DoctorRepository doctors;
	
	/**
	 * This service is called from a patient client that wants to push 
	 * his new data to the server
	 */
	@Override
	@RequestMapping(value=PatientSvcApi.PATIENTS_SVC_PATH, method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public Void updatePatient(@RequestBody Patient patient) {
		// TODO Auto-generated method stub
		System.out.println("inside updatePatient");
		

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); 
	    User user = users.findByUsername(username);
	    final int recordId = user.getRecordNumber();
		
		System.out.println("return patient with name " + patients.findByRecordId(recordId).getPatientName());
		
		Patient p = patients.findByRecordId(recordId);
		
		p.setLastCheckinDatetime(patient.getLastCheckinDatetime());
		p.setModerateToSeverePainMinutes(patient.getModerateToSeverePainMinutes());
		p.setNoEatMinutes(patient.getNoEatMinutes());
		p.setPainLevel(patient.getPainLevel());
		p.setServerTimestamp(getTimestamp());
		p.setSeverePainMinutes(patient.getSeverePainMinutes());
		p.setStoppedEatingLevel(patient.getStoppedEatingLevel());
		p.setSyncAction(patient.getSyncAction());
		p.setSynced(patient.getSynced());
		
		patients.save(p);
		
		
		System.out.println("patient saved, return");
		return null;
	}

	
	/**
	 * This service is called to get updated patient from the server
	 */
	@Override
	@RequestMapping(value=PatientSvcApi.PATIENT_BY_ID_SVC_PATH, method=RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public @ResponseBody Patient getPatient(@PathVariable("id") int id) {
		// TODO Auto-generated method stub
		System.out.println("inside getPatient: " + id);
		
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); 
	    User user = users.findByUsername(username);
	    final int recordId = user.getRecordNumber();
		
		System.out.println("return patient: " + patients.findByRecordId(recordId));*/
		
		return patients.findByRecordId(id);
	}
	
	
	/**
	 * This service is called to get a list of recent updated patients 
	 * belonging to a doctor form the server
	 */
	@Override
	@RequestMapping(value=PatientSvcApi.PATIENTS_SVC_PATH, method=RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public @ResponseBody Collection<Patient> getUpdatedPatients(@RequestParam String fromTimestamp) {
		// TODO Auto-generated method stub
		
		ArrayList<Patient> updatedPatients = new ArrayList<Patient>();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); 
	    
	    User user = users.findByUsername(username);
	    final long doctorId = user.getRecordNumber();
	    
	    System.out.println("inside getUpdatedPatients FROM " + fromTimestamp + " FOR doctorId " + doctorId  );
		
	    
	    System.out.println("doctorId is " + doctorId);
		
	    updatedPatients = patients.getAllByDoctorIdAndServerTimestampGreaterThan(doctorId, fromTimestamp);
				
		System.out.println("return " + updatedPatients.size() + " new patients updated");
		return updatedPatients;
		
	}

	

	/* Status codes */
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No patient is found")  // 404
	public class PatientNotFoundException extends RuntimeException {
		
       private static final long serialVersionUID = 1L;
		public PatientNotFoundException() {
        	super("No patient is found");
        }
    }

	private String getTimestamp() {
		return (new Timestamp((new Date()).getTime()).toString());
	}
	


}
