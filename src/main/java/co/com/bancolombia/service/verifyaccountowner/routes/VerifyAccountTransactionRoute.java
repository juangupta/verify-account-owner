package co.com.bancolombia.service.verifyaccountowner.routes;

import java.util.HashMap;
import java.util.Map;

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
	
	public void configure() throws Exception {
        from("direct:verify-account-transaction")
        
        .bean(verifyAccountOwnerBean, "updateAttributesRequestService(${body})")

        //Call channel service
        .log(">>>1  ${body}")  
        .to("direct:validate-channel-service")    	
        .log(">>>2 ${body}")
        .bean(verifyAccountOwnerBean, "updateAttributesChannelService(${body}, ${header.Error}, ${header.desc-error})")        
        
        
        //Call account service DataPower
        .log(">>>3  ${body}")  
        .choice()
        	.when().simple("${body['IsActive']} == true && ${body['Error']} == '0000' ")
        	 	.to("direct:soap-deposit-account-query")
        	 	.log(">>>4  ${body}")  
	            .bean(verifyAccountOwnerBean, "getAttributes()")
	            
	            //Transform JsonApi Response Service
	            .log(">>>5  ${body}")  
	            .to("freemarker:"+responseSuccessTemplate)
	            .unmarshal().json(JsonLibrary.Jackson, JsonApiResponse.class)
	            .log(">>>6 ${body}")
	            
            .otherwise()
            	//.log(">>>7  ${body}")  
            	//.to("freemarker:"+responseErrorTemplate)
	        	//.unmarshal().json(JsonLibrary.Jackson, JsonApiResponse.class)
	        	//.log(">>>8 ${body}")
            	.to("direct:generate-response-error")
            	
        .end();
        
    	//Mapero de Errores
        from("direct:generate-response-error")
	        .log(">>>7  ${body}")  
	        .to("freemarker:"+responseErrorTemplate)
	        .unmarshal().json(JsonLibrary.Jackson, JsonApiResponse.class)
	        .log(">>>8 ${body}");
        
    }
}