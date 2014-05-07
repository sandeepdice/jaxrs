package com.sandy.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sandy.model.User;

@Path("/xml/userjson")
public class JsonService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User generateJson()
	{
		User user = new User();
		user.setAge("10");
		user.setUserName("user");
		return user;
	}
}
