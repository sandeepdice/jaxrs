package com.sandy.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sandy.Main;

import static org.junit.Assert.assertEquals;

public class PackageTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
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

    @After
    public void tearDown() throws Exception {
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
}
