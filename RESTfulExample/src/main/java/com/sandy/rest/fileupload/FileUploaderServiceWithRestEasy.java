package com.sandy.rest.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("uploadresteasy")
public class FileUploaderServiceWithRestEasy{

	final String FILE_PATH_PREFIX = "/tmp";
		
	@Path("upload")
	@POST
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) throws IOException {
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		for(InputPart inputPart : uploadForm.get("uploadedFile")) {
			MultivaluedMap<String, String> header = inputPart.getHeaders();
			String fileName = new String();
			fileName = getFileName(header);
			
			InputStream inputStream = inputPart.getBody(InputStream.class, null);
			
			byte [] bytes = IOUtils.toByteArray(inputStream);
			fileName = FILE_PATH_PREFIX + fileName;
			writeFile(bytes,fileName);
 
			System.out.println("Done with file upload using rest easy");
		}
		return null;
	}
	
	private void writeFile(byte[] content, String filename) throws IOException {
		 
		File file = new File(filename);
 
		if (!file.exists()) {
			file.createNewFile();
		}
 
		FileOutputStream fop = new FileOutputStream(file);
 
		fop.write(content);
		fop.flush();
		fop.close();
	}
	
	/**
	 * header sample
	 * {
	 * 	Content-Type=[image/png], 
	 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
	 * }
	 **/
	//get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) {
 
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
 
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
 
				String[] name = filename.split("=");
 
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}
}