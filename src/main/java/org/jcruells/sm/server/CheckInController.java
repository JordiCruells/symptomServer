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

import org.jcruells.sm.server.client.CheckInSvcApi;
import org.jcruells.sm.server.client.SymptomDataContract;
import org.jcruells.sm.server.repository.CheckIn;
import org.jcruells.sm.server.repository.CheckInRepository;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CheckInController implements CheckInSvcApi {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CheckInRepository checkins;
	
	@Autowired
	private UserRepository users;
	
	@Autowired
	private DoctorRepository doctors;
	
	@Autowired
	private PatientRepository patients;
	
	
	@Override
	@RequestMapping(value=CheckInSvcApi.CHECKINS_SVC_PATH, method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public Void addNewCheckIns(@RequestBody Collection<CheckIn> newCheckins) {
		// TODO Auto-generated method stub
		
		System.out.println("inside add new checkins");
		
		Iterator<CheckIn> i = newCheckins.iterator();
		CheckIn c;
		
		while (i.hasNext()) {
			c =  i.next();
			
			
			//Only insertion of new check-ins is considered in this version 
			if (c.getSyncAction() == SymptomDataContract.SYNC_INSERT) {
				c.setServerTimestamp(getTimestamp()); // Send null to force the DBEngine to fill with current timestamp
				
				System.out.println("about to insert new checkin");
				System.out.println("record id " + c.getPatientRecordId());
				System.out.println("datetime checkin " + c.getCheckinDatetime());
				System.out.println("medication answers " + c.getMedicationAnswers());
				checkins.save(c);
				System.out.println("checkin inserted with server timestamp = " + c.getServerTimestamp());
			}
		}
		
		return null;
	}

	@Override
	@RequestMapping(value=CheckInSvcApi.CHECKINS_SVC_PATH, method=RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public @ResponseBody Collection<CheckIn> getNewCheckIns(@RequestParam String fromTimestamp) {
		// TODO Auto-generated method stub
		System.out.println("inside get new getNewCheckins from timestamp " + fromTimestamp);
		ArrayList<CheckIn> newCheckins = new ArrayList<CheckIn>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); 
	 
	    User user = users.findByUsername(username);
	    final long userId = user.getRecordNumber();
		
	    //Update last client timestamp for the doctor
		Doctor d = doctors.findByUserId(userId);		
		d.setClientLastSyncTimestamp(getTimestamp());
		doctors.save(d);
		
		System.out.println("doctor " + d.getUserId() + " updated with sync timestamp " + d.getClientLastSyncTimestamp());
		
		ArrayList<Patient> doctorPatients = patients.findAllByDoctorId(userId);
		Iterator<Patient> iPatients = doctorPatients.iterator();
		Patient p;
		ArrayList<CheckIn> pCheckins;
		
		while(iPatients.hasNext()) {
			p = iPatients.next();
			pCheckins = checkins.getAllByPatientRecordIdAndServerTimestampGreaterThan(p.getRecordId(), fromTimestamp);
			System.out.println("Next patient is " + p.getPatientName() + " " + p.getPatientLastName() 
					            + " which has " + pCheckins.size() +" checkins");
			newCheckins.addAll(pCheckins);
			
		}
				
		System.out.println("return " + newCheckins.size() + " new checkins");
		return newCheckins;
	}
	

	/* Status codes */
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No checkins found")  // 404
	public class CheckInNotFoundException extends RuntimeException {
		
       private static final long serialVersionUID = 1L;
		public CheckInNotFoundException() {
        	super("No checkins found");
        }
    }

	private String getTimestamp() {
		return (new Timestamp((new Date()).getTime()).toString());
	}
	
}
