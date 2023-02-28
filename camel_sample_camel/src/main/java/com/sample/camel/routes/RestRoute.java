package com.sample.camel.routes;

import java.io.IOException;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.camel.models.HelloModel;
import com.sample.camel.processor.ExceptionProcessor;
import com.sample.camel.processor.HelloProcessor;
import com.sample.camel.processor.ModelProcessor;


public class RestRoute extends RouteBuilder{
	
	
	String case6_url = "undertow:http://localhost:8080/camel_sample_rest-1.0-SNAPSHOT/RestClient/cameltest/rest1/";
	String case9_url = "undertow:http://localhost:8080/camel_sample_rest-1.0-SNAPSHOT/RestClient/cameltest/rest1/";
	
	@Override
    public void configure() throws Exception {
		
		CamelContext camelContext = new DefaultCamelContext();
		Logger logger = LoggerFactory.getLogger("testLogger");
		
		/* 
		 * 現状、ポート番号を指定すると落ちてしまったり、
		 * ログ出力の制御をしようとすると落ちてしまったり、
		 * バインディングモードの指定もできないため、
		 * できる状態で検証を進めた。
		 * おそらく、CamelContextにさまざまなものを登録しておかないといけないが
		 * それらを行っていないため、その辺りについては改めての確認が必要。
		 * port番号を指定しようとするとTypeConverterがないエラーで落ちるため、ランダム。
		 * lsof -i | grep java で検索が必要。（なぜ？）
		*/
		
		//undertow uses.(jboss also use it as subsystem.)
		restConfiguration().component("undertow")
				.host("localhost")
				.contextPath("/undertow");
		
			//case1 
			rest("/test1").description("rest test1 POST")
				.consumes("application/json").produces("application/json")
				.post()
					.to("direct:post")
					.to("direct:post_test1");
				
					from("direct:post")
						.setBody().constant("direct post is coming.")
						.to("stream:out")
						.setBody().constant("direct post is coming, again.")
						.to("stream:out");
					
					from("direct:post_test1")
						.setBody().constant("direct post_test1 is coming.")
						.to("stream:out")
						.setBody().constant("direct post_test1 is coming, again.")
						.to("stream:out")
						.end();
			
			//case2
			rest("/test2").description("rest test2 GET")
				.consumes("application/json").produces("application/json")
				.get()
					.to("direct:get")
					.to("direct:get2")
					.to("direct:get_test2");
				
					//same msg.
					from("direct:get")
						.setBody().constant("direct get is coming.")
						.to("stream:out");
					
					//same msg.
					from("direct:get2")
						.setBody().constant("direct get2 is coming.")
						.to("log:out");
					
					//do not work. Maybe, logger register problem. 
					from("direct:get_test2")
						//.log(LoggingLevel.ERROR,logger,"get_test2 test errorlog")
						.end();
			
			//case3
			rest("/test3").description("rest test3 GET")
				.consumes("application/json").produces("application/json")
				.get()
					.to("direct:curl")
				.get("/ex")
					.to("direct:curl")
					.to("direct:ex");
				
					from("direct:curl")
						.setBody().constant("direct curl is coming.")
						.to("stream:out");
					
					from("direct:ex")
						.setBody().constant("EX IS COMMING. THIS IS IMPORTANT.")
						.to("stream:out");
			
			//case4
			rest("/test4").description("rest test4 GET")
				.consumes("application/json").produces("application/json")
				.get()
					.to("direct:process");
				
					from("direct:process")
						.setBody().constant("direct process is coming.")
			        	.process(new HelloProcessor())
			        	.to("stream:out")
			        	.end();
				
			//case5
			//https://camel.apache.org/manual/try-catch-finally.html
			rest("/test5").description("rest test5 GET")
				.consumes("application/json").produces("application/json")
				.get()
					.to("direct:try-catch1");
				
					from("direct:try-catch1")
						.doTry()
							.setBody().constant("direct try-catch1 is coming.")
							.to("stream:out")
				        	.process(new ExceptionProcessor())
				        .doCatch(IOException.class, IllegalStateException.class)
				        	//.log(LoggingLevel.ERROR,logger,"try-catch1 errorlog")
				        	.setBody().constant("try-catch1 catch")
				        	.to("stream:out")
				        .doFinally()
					        .setBody().constant("try-catch1 finally")
				        	.to("stream:out");

			
			//case6
			rest("/test6").description("rest test6 GET")
				.consumes("application/json").produces("application/json")
				.get()
					.to("direct:try-catch2");
				
					from("direct:try-catch2")
						.doTry()
							.setBody().constant("direct try-catch2 is coming.")
							.to("stream:out")
							.setHeader(Exchange.HTTP_METHOD, constant("POST"))
					        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
					        .setHeader("Authorization", constant("access_token"))
							.to(case6_url)
				        .doCatch(Exception.class)
				        	//.log(LoggingLevel.ERROR,logger,"try-catch2 errorlog")
				        	.setBody().constant("try-catch2 catch")
				        	.to("stream:out")
				        .doFinally()
					        .setBody().constant("try-catch2 finally")
				        	.to("stream:out");
				
			
			//case7
			rest("/test7").description("rest test7")
				.consumes("application/json").produces("application/json")
				.get()
					.to("direct:please")
				.post()
					.to("direct:model-processing");
			
			
					from("direct:please")
						.transform().constant("please use POST method.")
						.end();
					
					from("direct:model-processing")
						.doTry()
							.setBody().constant("direct model-processing is coming.")
							.to("stream:out")
							.process(new ModelProcessor())
				        .doCatch(Exception.class)
				        	//.log(LoggingLevel.ERROR,logger,"model-processing errorlog")
				        	.setBody().constant("model-processing catch")
				        	.to("stream:out")
				        .doFinally()
					        .setBody().constant("model-processing finally")
				        	.to("stream:out");
					
			
			//case8
			rest("/test8").description("rest test8 POST")
				.consumes("application/json").produces("application/json")
				.post()
					.to("direct:choice8");
			
					from("direct:choice8")
						.choice()
							.when(simple("${header.Authorization} == 'access_token'"))
				            	.to("direct:ok8")
				            .when(body().contains("id"))
				            	.to("direct:ok8")
				            .otherwise()
				            	.to("direct:ng8")
						.endChoice();
					
						from("direct:ok8")
							.transform().constant("OK!").end();
						
						from("direct:ng8")
							.transform().constant("NG...").end();
						
			
			//case9
			rest("/test9").description("rest test9 POST")
				.consumes("application/json").produces("application/json")
				.post()
					.to("direct:choice9");
			
					from("direct:choice9")
						.choice()
				            .when(body().contains("id"))
				            	.to("direct:ok9")
				            .otherwise()
				            	.to("direct:ng9")
						.endChoice();
							
						
							from("direct:ok9")
								.doTry()
									.setBody().constant("direct try-catch2 is coming.")
									.to("stream:out")
									.setHeader(Exchange.HTTP_METHOD, constant("POST"))
							        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
							        .setHeader("Authorization", constant("access_token"))
									.to(case6_url)
								.doCatch(Exception.class)
									.setBody().constant("direct model-processing is coming.")
									.to("stream:out")
								.doFinally()
									.setBody().constant("model-processing finally")
						        	.to("stream:out");
							
							
							from("direct:ng9")
								.transform().constant("NG...").end();
							
			//case10
			rest("/test10").description("rest test10 POST")
				.consumes("application/json").produces("application/json")
				.post().type(HelloModel.class)
					.to("direct:choice10");
			
					from("direct:choice10")
						.choice()
				            .when(body().contains("id"))
				            	.to("direct:ok10")
				            .otherwise()
				            	.to("direct:ng10")
						.endChoice();
							
						from("direct:ok10")
							.transform().constant("OK!").end();
						
						
						from("direct:ng10")
							.transform().constant("NG...").end();
						
			
			
    }
	
}