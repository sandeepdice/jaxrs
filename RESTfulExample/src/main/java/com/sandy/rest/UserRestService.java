package com.sandy.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserRestService {

	@GET
	public Response getUser() {
		return Response.status(200).entity("getUser called").build();
	}
	
	@GET
	@Path("/vip")
	public Response getVipUser() {
		return Response.status(200).entity("getVipUser called").build();
	}
	
	@GET
	@Path("/{name}")
	public Response getUserByName(@PathParam("name") String name) {
		return Response.status(200).entity("getUserByName is called with: " + name).build();
	}
	
	@GET
	@Path("{year}/{month}/{date}")
	public Response echoDate(@PathParam("year") String year, @PathParam("month") String month, @PathParam("date") String date) {
		return Response.status(200).entity("year: " + year + " month: " + month + " day: " + date + " are passed to URL").build();
	}
}
