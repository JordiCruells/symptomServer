package org.jcruells.sm.server.repository;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class PatientMedication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int patientRecordId;
	private int patientMedicationId;
	private String patientMedicationFrom;
	private String patientMedicationTo;
	private String serverTimestamp;
	private int syncAction;
	
	@Transient
	private int synced;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPatientRecordId() {
		return patientRecordId;
	}

	public void setPatientRecordId(int patientRecordId) {
		this.patientRecordId = patientRecordId;
	}

	public int getPatientMedicationId() {
		return patientMedicationId;
	}

	public void setPatientMedicationId(int patientMedicationId) {
		this.patientMedicationId = patientMedicationId;
	}

	public String getPatientMedicationFrom() {
		return patientMedicationFrom;
	}

	public void setPatientMedicationFrom(String patientMedicationFrom) {
		this.patientMedicationFrom = patientMedicationFrom;
	}

	public String getPatientMedicationTo() {
		return patientMedicationTo;
	}

	public void setPatientMedicationTo(String patientMedicationTo) {
		this.patientMedicationTo = patientMedicationTo;
	}

	public String getServerTimestamp() {
		return serverTimestamp;
	}

	public void setServerTimestamp(String serverTimestamp) {
		this.serverTimestamp = serverTimestamp;
	}

	public int getSyncAction() {
		return syncAction;
	}

	public void setSyncAction(int syncAction) {
		this.syncAction = syncAction;
	}

	public int getSynced() {
		return synced;
	}

	public void setSynced(int synced) {
		this.synced = synced;
	}

	public PatientMedication() {}
	public PatientMedication(long id, int patientRecordId, int patientMedicationId, String patientMedicationFrom,
			String patientMedicationTo, String serverTimestamp, int syncAction, int synced) {
		super();
		this.id = id;
		this.patientRecordId = patientRecordId;
		this.patientMedicationId = patientMedicationId;
		this.patientMedicationFrom = patientMedicationFrom;
		this.patientMedicationTo = patientMedicationTo;
		this.serverTimestamp = serverTimestamp;
		this.syncAction = syncAction;
		this.synced = synced;
	}
	
		
	
	
}
