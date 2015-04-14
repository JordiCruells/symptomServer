package org.jcruells.sm.server.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * An interface for a repository that can store Users
 * objects and allow them to be searched
 * 
 * There's no need to use a RepositoryRestResource annotation because
 * we will use a controller to access this repository
 * 
 * @author jordi
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	
	public User findByUsername(String username);
	/*
	 * See: http://docs.spring.io/spring-data/jpa/docs/1.3.0.RELEASE/reference/html/jpa.repositories.html 
	 * for more examples of writing query methods
	 */
	
}
