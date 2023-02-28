package com.sample.camel.routes;

import org.apache.camel.builder.RouteBuilder;

public class HelloRoute extends RouteBuilder{
	
	
	@Override
    public void configure() throws Exception {
		from("stream:in?promptMessage=Enter : ")
        .to("stream:out")
        .to("stream:out")
		.to("log:out");
    }
	
}