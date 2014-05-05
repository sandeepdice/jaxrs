package com.sandy.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.streams.BufferedInput;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sandy.Main;
import com.sandy.model.User;
import com.temp.FileUploadService;

import static org.junit.Assert.assertEquals;

public class TestCases {
	

    private static HttpServer server;
    private static WebTarget target;

    /**
	 * a construction of a Client instance, from which a WebTarget is created, from which a request Invocation is built and invoked can be chained in a single "flow" of invocations. 
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testHelloWorld() {
        String responseMsg = target.path("hello/sandy").request().get(String.class);
        assertEquals("Jersey say : sandy", responseMsg);
    }
    
    @Test
    public void testHeaderParams() {
        String responseMsg = target.path("headerparam/get").request().get(String.class);
        assertEquals("echoUserAgentParam is called, userAgent : Jersey/2.8 (HttpUrlConnection 1.7.0_51)", responseMsg);
    }
    
    @Test
    public void testHeaderParamsGetByContext() {
        String responseMsg = target.path("headerparam/getByContext").request().get(String.class);
        assertEquals("Jersey/2.8 (HttpUrlConnection 1.7.0_51)", responseMsg);
    }  
    
    @Test
    public void testMatrixParam() {
        String responseMsg = target.path("matrixparam;param1=p1;param2=p2").request().get(String.class);
        assertEquals("Received following matrix params: \n param1: p1 \nparam2: p2", responseMsg);
    }
    
    /**
	 * In the Example a Form
	 * instance is created with two form parameters. Once ready, the Form
	 * instance is POSTed to the target resource. First, the acceptable media
	 * type is specified in the request(...) method. Then in the post(...)
	 * method, a call to a static method on JAX-RS Entity is made to construct
	 * the request entity instance and attach the proper content media type to
	 * the form entity that is being sent. The second parameter in the post(...)
	 * method specifies the Java type of the response entity that should be
	 * returned from the method in case of a successful response. In this case
	 * an instance of JAXB bean is requested to be returned on success. The
	 * Jersey client API takes care of selecting the proper MessageBodyWriter<T>
	 * for the serialization of the Form instance, invoking the POST request and
	 * producing and de-serialization of the response message payload into an
	 * instance of a JAXB bean using a proper MessageBodyReader<T>.
	 */
    @Test
    public void testFormService() {
        String name="foo";
        int age = 23;
        Form form = new Form();
        form.param("name", name);
        form.param("age", "23");
        String responseMsg = target.path("user/add").request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

        assertEquals("addUser is called with: " + name + " age: " + age, responseMsg);
    }
    
 	@Test
    public void testQueryParams() {
    	String from="a";
    	String to="b";
    	
        String responseMsg = target.path("usersquery/query").queryParam("from", "a").queryParam("to", "b").request().get(String.class);
        
        assertEquals("Received query params from: " + from + " to: " + to, responseMsg);
    }
 	
 	@Test
 	public void testHeaderParamService() {
 		String userAgentName = "mytestuseragent";
 		Invocation.Builder invocationBuilder =
 				target.path("/headerparam/get").request(MediaType.TEXT_PLAIN_TYPE);
 		invocationBuilder.header("user-agent", userAgentName);
 		
 		Response response = invocationBuilder.get();
 		assertEquals("echoUserAgentParam is called, userAgent : "+userAgentName, response.readEntity(String.class));
 	}
 	
 	@Test
 	public void testPlainTextFileDownloadService() {
 		Response response = target.path("downloader/textfile").request().get();
 		InputStream stream = response.readEntity(InputStream.class);
 		BufferedInputStream bis = new BufferedInputStream(stream);
 	}
 	
 	// TODO
 	public void testFileUpload() {
 		FormDataMultiPart fdmp = new FormDataMultiPart();
 		File file = new File("./pom.xml");
 		if (file != null) {
 		    fdmp.bodyPart(new FileDataBodyPart("file", file, MediaType.APPLICATION_OCTET_STREAM_TYPE));
 		}
 		
 		//ClientResponse response = target.path("file/upload").request(MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class, fdmp);
 		ClientResponse response = target.path("file/upload").request(MediaType.MULTIPART_FORM_DATA_TYPE).post(Entity.entity(fdmp, MediaType.APPLICATION_FORM_URLENCODED_TYPE), ClientResponse.class);
 		String responseStr = response.readEntity(String.class);
// 		assertEquals("File uploaded to: "+FileUploadService.FILE_FULL_PATH, responseStr);
 	}
 	
 	@Test
 	public void testXmlService() {
 		Response response = target.path("xml/user").request(MediaType.APPLICATION_XML_TYPE).get();
 		User responseStr = response.readEntity(User.class);
 		assertEquals("File uploaded to: ", responseStr);
 	}
}
