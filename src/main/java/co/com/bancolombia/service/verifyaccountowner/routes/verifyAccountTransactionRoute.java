package co.com.bancolombia.service.verifyaccountowner.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import co.com.bancolombia.service.verifyaccountowner.model.client.JsonApiResponse;

@Component
public class verifyAccountTransactionRoute extends RouteBuilder{
	
	public void configure() throws Exception {
        from("direct:verify-account-transaction")
        .log("TransactionRoute Test");        
    }
}
