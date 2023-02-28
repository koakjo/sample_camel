package com.sample.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ModelProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		exchange.getMessage(String.class);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(exchange.getMessage(String.class)));
		
		
		
	}
	
}