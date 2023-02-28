package com.sample.camel.routes;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

public class InstantRestRoute extends EndpointRouteBuilder{
	
	@Override
	public void configure() {
		
		restConfiguration().component("undertow")
			.host("localhost")
			.contextPath("/undertow");
		
			rest("/hello")
				.post()
					.to("direct:instant");
					
					from("directinstant")
						.setBody().constant("direct post is coming.")
						.to("stream:out");
	}
	
}