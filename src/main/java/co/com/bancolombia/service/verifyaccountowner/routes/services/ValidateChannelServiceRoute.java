package co.com.bancolombia.service.verifyaccountowner.routes.services;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.ZipDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.bancolombia.service.verifyaccountowner.model.client.channel.JsonApiResponse;


@Component
public class ValidateChannelServiceRoute extends RouteBuilder {
	
	static final String ACCEPT = "Accept";
	
	@Override
    public void configure() throws Exception {
        from("direct:validate-channel-service")
	        //.marshal().json(JsonLibrary.Jackson)    
    		.log("Route validateChannel: Before transform Request")        
        .to("freemarker:templates/ChannelServiceRq.ftl")    
        	.log("Route validateChannel: After transform Request")
	        //.log("Route validateChannel: Body Request ${body}")
	        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
	        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))	        
	        .setHeader(this.ACCEPT, constant("application/json"))
	    .to("http://localhost:8081/test/ValidateChannel")
	    	.unmarshal().json(JsonLibrary.Jackson, JsonApiResponse.class);
        
    }
}