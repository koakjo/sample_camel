package com.sample.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class HelloProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		System.out.println("hello, processor!");
		System.out.println(exchange.getMessage());
		
	}
	
}