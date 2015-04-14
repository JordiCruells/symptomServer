/*
 * 
 */

package org.jcruells.sm.server;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.jcruells.sm.server.client.MedicationSvcApi;
import org.jcruells.sm.server.client.SymptomDataContract;
import org.jcruells.sm.server.repository.Patient;
import org.jcruells.sm.server.repository.PatientMedication;
import org.jcruells.sm.server.repository.PatientMedicationRepository;
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
public class MedicationController implements MedicationSvcApi {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private PatientMedicationRepository medications;
	
	@Autowired
	private UserRepository users;
	
	@Autowired
	private PatientRepository patients;
	
	@Override
	@RequestMapping(value=MedicationSvcApi.MEDICATIONS_SVC_PATH, method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public Void updatePatientMedications(@RequestBody Collection<PatientMedication> requestMedications) {
		// TODO Auto-generated method stub
		System.out.println("inside updatePatientMedications");
		System.out.println("received " + requestMedications.size() + "medications ");
		
		Iterator<PatientMedication> iMedications = requestMedications.iterator();
		PatientMedication medication;
		PatientMedication updateMedication;
		
		while(iMedications.hasNext()) {
			medication = iMedications.next();
			
			switch(medication.getSyncAction()) {
				case SymptomDataContract.SYNC_INSERT:
					medication.setServerTimestamp(getTimestamp());
					medication.setSyncAction(SymptomDataContract.SYNC_INSERT);
					medications.save(medication);
					System.out.println("medication inserted - patient medication id " + medication.getPatientMedicationId());
					System.out.println("medication inserted - patient record id " + medication.getPatientRecordId());
					System.out.println("medication inserted - patient medication from " + medication.getPatientMedicationFrom());
					System.out.println("medication inserted - patient medication to " + medication.getPatientMedicationTo());
					break;
				case SymptomDataContract.SYNC_UPDATE:
					System.out.println("SYNC_UPDATE");
					System.out.println("medication.getPatientRecordId() : " + medication.getPatientRecordId());
					System.out.println(" medication.getPatientMedicationId() : " +  medication.getPatientMedicationId());
					updateMedication = medications.findByPatientRecordIdAndPatientMedicationId(medication.getPatientRecordId(), medication.getPatientMedicationId());
					if (updateMedication != null) {
						updateMedication.setServerTimestamp(getTimestamp());
						updateMedication.setSyncAction(SymptomDataContract.SYNC_UPDATE);
						updateMedication.setPatientMedicationTo(medication.getPatientMedicationTo());
						medications.save(updateMedication);
						System.out.println("medication updated - patient medication id " + updateMedication.getPatientMedicationId());
						System.out.println("medication updated - patient record id " + updateMedication.getPatientRecordId());
						System.out.println("medication updated - patient medication from " + updateMedication.getPatientMedicationFrom());
						System.out.println("medication updated - patient medication to " + updateMedication.getPatientMedicationTo());
					}
					break;
				default:
					break;
			}
		}
		
		System.out.println("return");
		return null;
	}

	
	/**
	 * This method returns new medications for the current logged patient if there are any
	 */
	@Override
	@RequestMapping(value=MedicationSvcApi.MEDICATIONS_SVC_PATH, method=RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public @ResponseBody Collection<PatientMedication> getNewMedications(@RequestParam String fromTimestamp) {
		// TODO Auto-generated method stub
		System.out.println("inside getNewMedications");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); 
	 
	    User user = users.findByUsername(username);
	    final int recordId = user.getRecordNumber();
		
	    //Update last client timestamp
		Patient p = patients.findByRecordId(recordId);
		p.setClientLastSyncTimestamp(getTimestamp());
		patients.save(p);
		
		return medications.findByPatientRecordIdAndServerTimestampGreaterThan(user.getRecordNumber(), fromTimestamp);
		
		
	}
	

	/* Status codes */
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No medications found")  // 404
	public class MedicationNotFoundException extends RuntimeException {
		
       private static final long serialVersionUID = 1L;
		public MedicationNotFoundException() {
        	super("No medications found");
        }
    }
	
	private String getTimestamp() {
		return (new Timestamp((new Date()).getTime()).toString());
	}
}
