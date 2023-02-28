package com.sample.camel.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
@Path("/cameltest")
public class CamelSampleResource {
	
	@Path("/rest1")
	@POST
	public Response rest1() {
		
		try {
    		System.out.println("camel_sample_rest rest1 is started. ------------------");
    		System.out.println("camel_sample_rest rest1 is completed. ------------------");
    		return Response.ok("camel_sample_rest REST API is successed.").build();
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("camel_sample_rest rest1 exception.>>>>>>>>>>>>>>>>>");
    		return Response.status(Status.BAD_REQUEST).build();
    	}
	}
	
	@Path("/rest2")
	@GET
	public Response rest2() {
		
		try {
    		System.out.println("camel_sample_rest rest2 is started. ------------------");
    		System.out.println("camel_sample_rest rest2 is completed. ------------------");
    		return Response.ok("camel_sample_rest REST API is successed.").build();
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("camel_sample_rest rest2 exception.>>>>>>>>>>>>>>>>>");
    		return Response.status(Status.BAD_REQUEST).build();
    	}
	}
	
}