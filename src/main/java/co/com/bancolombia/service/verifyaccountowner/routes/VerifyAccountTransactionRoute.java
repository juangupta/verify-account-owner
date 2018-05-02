package co.com.bancolombia.service.verifyaccountowner.routes;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.com.bancolombia.service.verifyaccountowner.beans.VerifyAccountOwnerBean;
import co.com.bancolombia.service.verifyaccountowner.model.JsonApiResponse;
import co.com.bancolombia.service.verifyaccountowner.model.client.ClientJsonApiResponse;

@Component
public class VerifyAccountTransactionRoute extends RouteBuilder{
	
	private VerifyAccountOwnerBean verifyAccountOwnerBean = new VerifyAccountOwnerBean();
	
	@Value("${verify.account.service.freemarker.response.success.template}")
    private String responseSuccessTemplate;

	@Value("${verify.account.service.freemarker.response.error.template}")
    private String responseErrorTemplate;
	

	private final String ERROR = "Error";
	private final String DESC_ERROR = "desc-error";
	
	public void configure() throws Exception {
        
		
		from("direct:verify-account-transaction")
        
        .bean(verifyAccountOwnerBean, "updateAttributesRequestService(${body})")

        //Call channel service
        .log(">>>1  ${body}")  
        .to("direct:validate-channel-service")    	
        .log(">>>2 ${body}")
        .log(">>>3  ${body}")  
        .log(">>>3.1  ${in.header.Error}")  
        .process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				// TODO Auto-generated method stub
				System.out.println(exchange);
				
			}
		})
        .choice()
              .when(header("Error").isEqualTo("0000"))
        	 	.to("direct:invoke-deposit-account-service")
            .otherwise()
            	
            	.to("direct:generate-response-error")            	
        .end();
		
		//Call account service DataPower
		from("direct:invoke-deposit-account-service")
		.to("direct:soap-deposit-account-query")
	 	.log(">>>4  ${body}")  
	 	.log(">>>4.1  ${in.header.Error}")  
		.choice()
			.when(header("Error").isEqualTo("0000"))	
				.to("direct:generate-response-success")
			.otherwise()
	        	.to("direct:generate-response-error")  			
		.end();
        
      //Success Response
        from("direct:generate-response-success")	            
	        //Transform JsonApi Response Service
	        .log(">>>5  ${body}")  
	        .to("freemarker:"+responseSuccessTemplate)
	        .unmarshal().json(JsonLibrary.Jackson, JsonApiResponse.class)
	        .log(">>>6 ${body}");
        
    	//Error Response
        from("direct:generate-response-error")
	        .log(">>>7  ${body}")  
	        .to("freemarker:"+responseErrorTemplate)
	        .unmarshal().json(JsonLibrary.Jackson, JsonApiResponse.class)
	        .log(">>>8 ${body}");
        
    }
}