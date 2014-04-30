package com.sandy.rest.fileupload;

import java.io.InputStream;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;

public interface FileUploader {
	public Response uploadFile(InputStream uploadedInputStream);
}