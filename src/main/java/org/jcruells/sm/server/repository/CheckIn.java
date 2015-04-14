package org.jcruells.sm.server.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

@Entity
public class CheckIn  {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int patientRecordId;
	private String checkinDatetime;
	private int painLevel;
	private int medicationTaken;
	private String medicationDatetime;
	private String medicationAnswers;
	private int stoppedEatingLevel;
	private String serverTimestamp;
	private int syncAction;
	
	@Transient
	private int synced;
	
	public CheckIn() {}

	public CheckIn(long id, int patientRecordId, String checkinDatetime, int painLevel, int medicationTaken,
			String medicationDatetime, String medicationAnswers, int stoppedEatingLevel, String serverTimestamp,
			int syncAction, int synced) {
		super();
		this.id = id;
		this.patientRecordId = patientRecordId;
		this.checkinDatetime = checkinDatetime;
		this.painLevel = painLevel;
		this.medicationTaken = medicationTaken;
		this.medicationDatetime = medicationDatetime;
		this.medicationAnswers = medicationAnswers;
		this.stoppedEatingLevel = stoppedEatingLevel;
		this.serverTimestamp = serverTimestamp;
		this.syncAction = syncAction;
		this.synced = synced;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		//System.out.println("set id");
		this.id = id;
	}

	public Integer getPatientRecordId() {
		return patientRecordId;
	}

	public void setPatientRecordId(Integer patientRecordId) {
		//System.out.prIntegerln("set patient id");
		this.patientRecordId = patientRecordId;
	}

	public String getCheckinDatetime() {
		
		return checkinDatetime;
	}

	public void setCheckinDatetime(String checkinDatetime) {
		//System.out.prIntegerln("set patient checkin datetime");
		this.checkinDatetime = checkinDatetime;
	}

	public Integer getPainLevel() {
		//System.out.prIntegerln("set painllevel");
		return painLevel;
	}

	public void setPainLevel(Integer painLevel) {
		this.painLevel = painLevel;
	}

	public Integer getMedicationTaken() {
		return medicationTaken;
	}

	public void setMedicationTaken(Integer medicationTaken) {
		//System.out.prIntegerln("set medication taken");
		this.medicationTaken = medicationTaken;
	}

	public String getMedicationDatetime() {
		return medicationDatetime;
	}

	public void setMedicationDatetime(String medicationDatetime) {
		//System.out.prIntegerln("set medication deteime");
		this.medicationDatetime = medicationDatetime;
	}

	public String getMedicationAnswers() {
		return medicationAnswers;
	}

	public void setMedicationAnswers(String medicationAnswers) {
		//System.out.prIntegerln("set medication answers");
		this.medicationAnswers = medicationAnswers;
	}

	public Integer getStoppedEatingLevel() {
		return stoppedEatingLevel;
	}

	public void setStoppedEatingLevel(Integer stoppedEatingLevel) {
		//System.out.prIntegerln("set stopped eating");
		this.stoppedEatingLevel = stoppedEatingLevel;
	}

	public String getServerTimestamp() {
		return serverTimestamp;
	}

	public void setServerTimestamp(String serverTimestamp) {
		//System.out.prIntegerln("set server timestamp");
		this.serverTimestamp = serverTimestamp;
	}

	public Integer getSyncAction() {
		return syncAction;
	}

	public void setSyncAction(Integer syncAction) {
		//System.out.prIntegerln("set sync action");
		this.syncAction = syncAction;
	}

	public Integer getSynced() {
		return synced;
	}

	public void setSynced(Integer synced) {
		//System.out.prIntegerln("set synced");
		this.synced = synced;
	}

	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(id);
	}

	/**
	 * Two Videos are considered equal if they have exactly the same values for
	 * their name, url, and duration.
	 * 
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CheckIn) {
			CheckIn other = (CheckIn) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(id, other.id);
		} else {
			return false;
		}
	}


}
