package com.sandy.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/usersquery")
public class UserService {
	@GET
	@Path("query")
	public Response testQueryParam(@QueryParam("from") String from, @QueryParam("to")String to) {
		return Response.status(200).entity("Received query params from: " + from + " to: " + to).build();
	}
}
