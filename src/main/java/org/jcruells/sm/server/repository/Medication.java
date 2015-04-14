package org.jcruells.sm.server.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Medication {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Medication(long id, String name, String serverTimestamp, int syncAction, int synced) {
		super();
		this.id = id;
		this.name = name;
		this.serverTimestamp = serverTimestamp;
		this.syncAction = syncAction;
		this.synced = synced;
	}
	public Medication() {}
	
	
	
	
	
}
