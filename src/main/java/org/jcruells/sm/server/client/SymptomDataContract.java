package org.jcruells.sm.server.client;

public class SymptomDataContract {

	public static final int ANSWER_PAIN_LEVEL_WELL_CONTROLLED = 1;
	public static final int ANSWER_PAIN_LEVEL_MODERATE = 2;
	public static final int ANSWER_PAIN_LEVEL_SEVERE = 3;
	
	public static final int ANSWER_STOPPED_EATING_NO = 1;
	public static final int ANSWER_STOPPED_EATING_SOME = 2;
	public static final int ANSWER_STOPPED_EATING_YES = 3;
	
	public final static int ANSWER_MEDICATION_TAKEN = 1;
	public final static int ANSWER_MEDICATION_NOT_TAKEN = 1;
	
	public final static String CHECKINS_TABLE = "checkins";
	public final static String ID = "_id";
	public final static String PATIENT_RECORD_ID = "patient_id";
	public final static String CHECK_IN_DATETIME = "datetime";
	public final static String PAIN_LEVEL = "pain_level";
	public final static String MEDICATION_TAKEN = "medication_taken";
	public final static String MEDICATION_DATETIME = "medication_time";
	public final static String MEDICATION_ANSWERS = "medication_answers";
	public final static String STOPPED_EATING_LEVEL = "stopped_eating_level";
	public final static String SERVER_TIMESTAMP = "server_timestamp";
	public final static String SYNC_ACTION = "sync_action";
	public final static int SYNC_INSERT = 1;	
	public final static int SYNC_UPDATE = 2;
	public final static String SYNCED = "synced";
	public final static int STATE_NOT_SYNCED = 0;
	public final static int STATE_SYNCED = 1;

	public final static String PATIENT_MEDICATIONS_TABLE = "patient_medications";
	public final static String PATIENT_MEDICATION_ID = "medication_id";
	public final static String PATIENT_MEDICATION_FROM = "medication_from";
	public final static String PATIENT_MEDICATION_TO = "medication_to";

	public final static String PATIENTS_TABLE = "patients";
	public final static String PATIENT_NAME = "patient_name";
	public final static String PATIENT_LAST_NAME = "patient_last_name";
	public final static String PATIENT_BIRTHDAY = "birthday";
	public final static String SEVERE_PAIN_MINUTES = "severe_pain_minutes";
	public final static String MODERATE_TO_SEVERE_PAIN_MINUTES = "moderate_severe_pain_minutes";
	public final static String NO_EAT_MINUTES = "no_eat_minutes";
	public final static String LAST_CHECKIN_DATETIME = "last_checkin_datetime";
	public final static String CLIENT_LAST_SYNC_TIMESTAMP = "client_last_sync_timestamp";
		
}
