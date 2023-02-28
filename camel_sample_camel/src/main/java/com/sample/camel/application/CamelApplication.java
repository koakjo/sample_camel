package com.sample.camel.application;


import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

import com.sample.camel.routes.HelloRoute;
import com.sample.camel.routes.RestRoute;

/**
 * Hello world!
 *
 */
public class CamelApplication
{
    public static void main( String[] args ) throws Exception{
			
		try (CamelContext camelContext = new DefaultCamelContext()) {
			
			/*
			TypeConverterRegistry tcr = camelContext.getTypeConverterRegistry();
			tcr.addTypeConverter(Integer.class, String.class, new StringConverter());
			
			camelContext.setTypeConverterRegistry(tcr);
			
			System.out.println(tcr.lookup(Integer.class, String.class));
			
			camelContext.addRoutes(new RestRoute());
			camelContext.start();
			*/
			
			Main main = new Main();
			
			System.out.println( "before main.run()");
			main.configure().addRoutesBuilder(new RestRoute());
			main.configure().addRoutesBuilder(new HelloRoute());
			main.run();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}


