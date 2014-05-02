package com.sandy.rest;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("headerparam")
public class HeaderParamService {
	/**
	 * Test URL: 
	 * http://localhost:8080/RESTfulExample/rest/headerparam/get
	 * @param userAgent
	 * @return
	 */
	@GET
	@Path("get")
	public Response echoUserAgentParam(@HeaderParam ("user-agent") String userAgent) {
 
		return Response.status(200)
			.entity("echoUserAgentParam is called, userAgent : " + userAgent)
			.build();
 
	}
	
	/**
	 * Test URL
	 * http://localhost:8080/RESTfulExample/rest/headerparam/getByContext
	 * @param header
	 * @return
	 */
	@GET
	@Path("getByContext")
	public Response echoUserAgentParam(@Context HttpHeaders header) {
 
		return Response.status(200)
			.entity(header.getRequestHeader("user-agent").get(0))
			.build();
 
	}
}