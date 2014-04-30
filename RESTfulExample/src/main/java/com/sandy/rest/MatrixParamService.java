package com.sandy.rest;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("matrixparam")
public class MatrixParamService {
	
	/**
	 * Test Url:
	 * http://localhost:8080/RESTfulExample/rest/matrixparam;param1=p1;param2=p2
	 * @param param1
	 * @param param2
	 * @return
	 */
	@GET
	public Response echoMatrixParams(@MatrixParam("param1") String param1, @MatrixParam("param2") String param2) {
		System.out.println("echoMatrixParams triggered with params: param1: " + param1 + " param2: " + param2);
		return Response.status(200).entity("Received following matrix params: \n param1: "+ param1 + " \nparam2: " + param2).build();
	}
}
