package co.com.bancolombia.service.verifyaccountowner.routes;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.com.bancolombia.service.verifyaccountowner.beans.VerifyAccountOwnerBean;
import co.com.bancolombia.service.verifyaccountowner.model.JsonApiResponse;

@Component
public class verifyAccountTransactionRoute extends RouteBuilder{
	
	private VerifyAccountOwnerBean verifyAccountOwnerBean = new VerifyAccountOwnerBean();
	
	@Value("${verify.account.service.freemarker.template}")
    private String template;
	
	public void configure() throws Exception {
        from("direct:verify-account-transaction")
        .bean(verifyAccountOwnerBean, "updateAttributesChannelService(${body})")
        .log(">>>1  ${body}")  
        .to("direct:validate-channel-service")        
        .log(">>>2 ${body}")
        .bean(verifyAccountOwnerBean, "getAttributes()")        
        .log(">>>3 ${body}")
        .to("direct:soap-deposit-account-query")  
        .log(">>>4 ${body}")
        
        .to("freemarker:"+template)
        .unmarshal().json(JsonLibrary.Jackson, JsonApiResponse.class)
        .log(">>>5 ${body}");
        
    }
}