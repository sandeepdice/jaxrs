package com.sandy.rest;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("downloader")
public class DownloaderService {

	@GET
	@Produces("text/plain")
	@Path("textfile")
	public Response sendTextFile() {
		ResponseBuilder response = Response.ok((Object) new File("/home/sandeep/jaxrs/RESTfulExample/rebuildAndDeploy.sh"));
		response.header("Content-Disposition", "attachment; filename=\"rebuildAndDeploy_from_server.sh\"");
		return response.build();
	}
	
	@GET
	@Produces("image/png")
	@Path("image")
	public Response sendImage() {
		ResponseBuilder response = Response.ok((Object) new File("/home/sandeep/jaxrs/RESTfulExample/favicon57x57.png"));
		response.header("Content-Disposition", "attachment; filename=\"favicon57x57_from_server.png\"");
		return response.build();
	}	
	
	@GET
	@Produces("application/pdf")
	@Path("pdf")
	public Response sendPdf() {
		ResponseBuilder response = Response.ok((Object) new File("/home/sandeep/jaxrs/RESTfulExample/Logo.pdf"));
		response.header("Content-Disposition", "attachment; filename=\"Pdf_from_server.pdf\"");
		return response.build();
	}		
}
