package org.jcruells.sm.server.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

	public Doctor findByUserId(long userId);
	
}



