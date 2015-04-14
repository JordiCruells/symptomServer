/*
 * 
 */

package org.jcruells.sm.server;

import javax.servlet.http.HttpServletRequest;

import org.jcruells.sm.server.client.UserSvcApi;
import org.jcruells.sm.server.repository.User;
import org.jcruells.sm.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@Controller
public class UserController implements UserSvcApi {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRepository users;
	
	
	/**
	 *GET /user
	 *  - Returns data for the requesting logged user.
	 *
	 */
	@Override
	@RequestMapping(value=UserSvcApi.USER_SVC_PATH, method=RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public @ResponseBody User getUserData() {
		
		System.out.println("getUserData()");
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); 
	 
	    System.out.println("userId: " + username);
	    
	    User user = users.findByUsername(username);
	    
	    System.out.println("FOUND USER, ID IS " + user.getId());
	    
	    if (user == null) System.out.println("user " + username + " not found !!!");
	    else System.out.println("user " + username + " is found !!!");
	    
		if (user == null) throw new UserNotFoundException(username);
		
		//System.out.println("Name is:" + user.getName());
		
		return user;
	}
	
	
	
	
	/**
	 *GET /video/{id}
	 *  - Returns the video with the given id or 404 if the video is not found.
	 *
	 */
	/*
	 -- GONNA LEAVE IT UNIMPLEMENTED FOR NOW -- 
	 
	@Override
	@RequestMapping(value=UserSvcApi.USER_SVC_PATH + "/{userId}", method=RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody User getUserByUserId(@PathVariable("userId") long id){
		
		User user = users.findOne(id);
		//Get the video from the repo using the given id
		if (user == null) throw new UserNotFoundException(id);
		
		return user;
	}*/
	
	

	/* Status codes */
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User not found")  // 404
	public class UserNotFoundException extends RuntimeException {
		
       private static final long serialVersionUID = 1L;
		public UserNotFoundException(String userId) {
        	super("User " + userId + " is not found");
        }
    }
	
	
}
