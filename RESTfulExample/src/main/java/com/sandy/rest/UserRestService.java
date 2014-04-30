package com.sandy.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserRestService {

	/**
	 * test url: http://localhost:8080/RESTfulExample/rest/users
	 * @return
	 */
	@GET
	public Response getUser() {
		return Response.status(200).entity("getUser called").build();
	}
	
	/**
	 * test url: http://localhost:8080/RESTfulExample/rest/users/vip
	 * @return
	 */
	@GET
	@Path("/vip")
	public Response getVipUser() {
		return Response.status(200).entity("getVipUser called").build();
	}
	
	/**
	 * test url: http://localhost:8080/RESTfulExample/rest/users/myname
	 * @return getUserByName is called with: myname
	 */
	@GET
	@Path("/{name}")
	public Response getUserByName(@PathParam("name") String name) {
		return Response.status(200).entity("getUserByName is called with: " + name).build();
	}
	
	/**
	 * Test url: http://localhost:8080/RESTfulExample/rest/users/2012/12/12
	 * @param year
	 * @param month
	 * @param date
	 * @return year: 2012 month: 12 day: 12 are passed to URL
	 */
	@GET
	@Path("{year}/{month}/{date}")
	public Response echoDate(@PathParam("year") String year, @PathParam("month") String month, @PathParam("date") String date) {
		return Response.status(200).entity("year: " + year + " month: " + month + " day: " + date + " are passed to URL").build();
	}
}
