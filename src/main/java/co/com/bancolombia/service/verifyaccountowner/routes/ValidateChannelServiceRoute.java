package co.com.bancolombia.service.verifyaccountowner.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.com.bancolombia.service.verifyaccountowner.model.client.ClientJsonApiResponse;


@Component
public class ValidateChannelServiceRoute extends RouteBuilder {
	
	private final String ACCEPT = "Accept";
	
	@Value("${channel.service.path}")
    private String path;
	
	@Value("${channel.service.freemarker.template}")
    private String template;
	
	@Override
    public void configure() throws Exception {
        from("direct:validate-channel-service")
	        //.marshal().json(JsonLibrary.Jackson)    
    		.log("Route validateChannel: Before transform Request")        
        .to("freemarker:"+template)    
        	.log("Route validateChannel: After transform Request >>>>> ${body}")
	        //.log("Route validateChannel: Body Request ${body}")
	        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
	        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))	        
	        .setHeader(this.ACCEPT, constant("application/json"))
	    .to(path)
	    	.unmarshal().json(JsonLibrary.Jackson, ClientJsonApiResponse.class);
        
    }
}