package com.sample.camel.filter;

import java.io.IOException;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@PreMatching
public class PreMatchingAuthFilter implements ContainerRequestFilter{
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("camel_sample_rest PreMatchingAuthFilter is started.");
	}
	
	public void filter(ContainerRequestContext requestContext) throws IOException {
 
		if(requestContext.getHeaderString("Authorization") == null){
			requestContext.abortWith(Response.status(403).build());
		}else{
			System.out.println("camel_sample_rest sAuthorization is OK.");
		}
	}
}