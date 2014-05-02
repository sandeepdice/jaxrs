package com.sandy.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserFormParamService {
	/**
	 * Test url
	 * POST
	 * http://localhost:8080/RESTfulExample/rest/user/add
	 * with 
	 * 		name=a
			age=1
	 * @param name
	 * @param age
	 * @return
	 */
	@POST
	@Path("/add")
	public Response addUser(@FormParam("name") String name, @FormParam("age") int age) {
		System.out.println("addUser triggered: name: " + name + " age: " + age);
		return Response.status(200).entity("addUser is called with: " + name + " age: " + age).build();
	}
}
