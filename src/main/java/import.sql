delete from user;
insert into user (username, password, record_number, role) values ('doctor1' , 'pwd_d1', 1, 'DOCTOR');
insert into user (username, password, record_number, role) values ('doctor2' , 'pwd_d1', 2, 'DOCTOR');
insert into user (username, password, record_number, role) values ('patient1', 'pwd_p1', 1101, 'PATIENT');
insert into user (username, password, record_number, role) values ('patient2', 'pwd_p2', 1102, 'PATIENT');
insert into user (username, password, record_number, role) values ('patient3', 'pwd_p3', 1103, 'PATIENT');
insert into user (username, password, record_number, role) values ('patient4', 'pwd_p4', 1104, 'PATIENT');
insert into user (username, password, record_number, role) values ('patient5', 'pwd_p5', 1105, 'PATIENT');
insert into user (username, password, record_number, role) values ('patient6', 'pwd_p6', 1106, 'PATIENT');
insert into user (username, password, record_number, role) values ('patient7', 'pwd_p7', 1107, 'PATIENT');
insert into user (username, password, record_number, role) values ('patient8', 'pwd_p8', 1108, 'PATIENT');
delete from patient;
insert into patient (record_id, doctor_id, patient_name, patient_last_name, patient_birthday, severe_pain_minutes, moderate_to_severe_pain_minutes, no_eat_minutes, pain_level, stopped_eating_level,last_checkin_datetime, client_last_sync_timestamp, server_timestamp, sync_action) values (1101, 1, 'Teresa', 'Romero', '1955-11-12', 13, 18, 15, 3, 3, '2014-10-16 13:35:22', '2014-11-25 13:35:22','2014-09-17 01:30:00', 1);
insert into patient (record_id, doctor_id, patient_name, patient_last_name, patient_birthday, severe_pain_minutes, moderate_to_severe_pain_minutes, no_eat_minutes, pain_level, stopped_eating_level,last_checkin_datetime, client_last_sync_timestamp, server_timestamp, sync_action) values (1102, 1, 'Maurice', 'Lucens', '1947-10-22', 4, 20, 5, 3, 3, '2014-10-16 13:35:22', '2014-11-25 13:35:22','2014-09-17 01:30:00', 1);
insert into patient (record_id, doctor_id, patient_name, patient_last_name, patient_birthday, severe_pain_minutes, moderate_to_severe_pain_minutes, no_eat_minutes, pain_level, stopped_eating_level,last_checkin_datetime, client_last_sync_timestamp, server_timestamp, sync_action) values (1103, 1, 'Ralph', 'Sanchez', '1962-01-22', 0, 0, 0, 1, 1, '2014-10-16 13:35:22', '2014-11-25 13:35:22','2014-09-17 01:30:00', 1);
insert into patient (record_id, doctor_id, patient_name, patient_last_name, patient_birthday, severe_pain_minutes, moderate_to_severe_pain_minutes, no_eat_minutes, pain_level, stopped_eating_level,last_checkin_datetime, client_last_sync_timestamp, server_timestamp, sync_action) values (1104, 2, 'Arthur', 'Moore', '1945-03-05', 1350, 1350, 0, 3, 3, '2014-10-16 13:35:22', '2014-11-25 13:35:22','2014-09-17 01:30:00', 1);
insert into patient (record_id, doctor_id, patient_name, patient_last_name, patient_birthday, severe_pain_minutes, moderate_to_severe_pain_minutes, no_eat_minutes, pain_level, stopped_eating_level,last_checkin_datetime, client_last_sync_timestamp, server_timestamp, sync_action) values (1105, 2, 'John', 'Rhydal', '1932-01-24', 1200, 1800, 1500, 3, 3, '2014-10-16 13:35:22', '2014-11-25 13:35:22','2014-09-17 01:30:00', 1);
delete from doctor;
insert into doctor (user_id, doctor_name, doctor_last_name, client_last_sync_timestamp, server_timestamp, sync_action) values (1, 'Jim', 'Adams', '2014-10-16 14:35:22','2014-11-25 13:35:220', 1);
insert into doctor (user_id, doctor_name, doctor_last_name, client_last_sync_timestamp, server_timestamp, sync_action) values (2, 'Alicia', 'Keys', '2014-10-16 14:35:22','2014-11-25 13:35:22', 1);
delete from patient_medication;
insert into patient_medication (patient_record_id, patient_medication_id, patient_medication_from, patient_medication_to, server_timestamp, sync_action) values (1101, 5, '2014-10-15', '9999-12-31', '2014-09-17 01:30:00', 1);
insert into patient_medication (patient_record_id, patient_medication_id, patient_medication_from, patient_medication_to, server_timestamp, sync_action) values (1101, 6, '2014-10-21', '9999-12-31', '2014-09-17 01:30:00', 1);
insert into patient_medication (patient_record_id, patient_medication_id, patient_medication_from, patient_medication_to, server_timestamp, sync_action) values (1101, 8, '2014-10-21', '9999-12-31', '2014-09-17 01:30:00', 1);
insert into patient_medication (patient_record_id, patient_medication_id, patient_medication_from, patient_medication_to, server_timestamp, sync_action) values (1104, 5, '2014-10-15', '9999-12-31', '2014-09-17 01:30:00', 1);
insert into patient_medication (patient_record_id, patient_medication_id, patient_medication_from, patient_medication_to, server_timestamp, sync_action) values (1104, 6, '2014-10-21', '9999-12-31', '2014-09-17 01:30:00', 1);
insert into patient_medication (patient_record_id, patient_medication_id, patient_medication_from, patient_medication_to, server_timestamp, sync_action) values (1105, 8, '2014-10-21', '9999-12-31', '2014-09-17 01:30:00', 1);
insert into patient_medication (patient_record_id, patient_medication_id, patient_medication_from, patient_medication_to, server_timestamp, sync_action) values (1105, 5, '2014-10-15', '9999-12-31', '2014-09-17 01:30:00', 1);
insert into patient_medication (patient_record_id, patient_medication_id, patient_medication_from, patient_medication_to, server_timestamp, sync_action) values (1105, 6, '2014-10-21', '9999-12-31', '2014-09-17 01:30:00', 1);
insert into patient_medication (patient_record_id, patient_medication_id, patient_medication_from, patient_medication_to, server_timestamp, sync_action) values (1105, 4, '2014-10-24', '9999-12-31', '2014-09-17 01:30:00', 1);