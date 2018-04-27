package co.com.bancolombia.service.verifyaccountowner.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.com.bancolombia.service.verifyaccountowner.model.JsonApiResponse;
import co.com.bancolombia.service.verifyaccountowner.model.client.ClientJsonApiResponse;


@Component
public class ValidateChannelServiceRoute extends RouteBuilder {
	
	private final String ACCEPT = "Accept";
	
	@Value("${channel.service.path}")
    private String path;
	
	@Value("${channel.service.freemarker.template}")
    private String template;
	
	@Value("${verify.account.service.freemarker.response.error.template}")
    private String responseErrorTemplate;

	
	@Override
    public void configure() throws Exception {
        from("direct:validate-channel-service")       
        .to("freemarker:"+template)
	        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
	        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))	        
	        .setHeader(this.ACCEPT, constant("application/json"))
	     
	    .log("Request Rest Channel Service ${body}")
	    .hystrix()
	    .hystrixConfiguration().executionTimeoutInMilliseconds(2000).end()
	    .to(path)
	    .log("Estamos melos")
        //.unmarshal().json(JsonLibrary.Jackson, ClientJsonApiResponse.class)
	    .onFallback()
			 // we use a fallback without network that provides a repsonse message immediately
			 //.transform().simple("Fallback ${body}") 
		    .to("freemarker:"+responseErrorTemplate)
		    .log("Hystrix ${body}")
	        .unmarshal().json(JsonLibrary.Jackson, JsonApiResponse.class)
        .end();
	    

        
    } 
}