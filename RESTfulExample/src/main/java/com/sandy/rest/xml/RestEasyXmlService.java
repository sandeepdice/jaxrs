package com.sandy.rest.xml;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sandy.model.Customer;
import com.sandy.model.User;

@Path("/xml/user")
public class RestEasyXmlService {

	@GET
	@Path("/get/{pin}")
	@Produces(MediaType.APPLICATION_XML)
	public User genXml(@PathParam("pin") int pin) {
		if(pin == 1)
		{
			User user = new User();
			user.setUsername("mkyong");
			user.setFirstname("Firstname");
			user.setLastname("lastname");
	 
			return user; 
		}
		else
			return null;
	}

}
