package com.sandy.rest.xml;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sandy.model.Customer;

@Path("/xml/customer")
public class JerseyXmlService {
	@GET
	@Path("{pin}")
	@Produces(MediaType.APPLICATION_XML)
	public Customer genXml(@PathParam("pin") int pin) {
		Customer customer = new Customer();
		customer.setName("mkyong");
		customer.setPin(pin);

		return customer;
	}
}
