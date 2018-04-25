package co.com.bancolombia.service.verifyaccountowner.routes;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.spring.ws.SpringWebserviceConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.grupobancolombia.ents.soi.coreextensions.v2.Destination;
import com.grupobancolombia.ents.soi.coreextensions.v2.UsernameToken;
import com.grupobancolombia.ents.soi.messageformat.v2.RequestHeader;

import co.com.bancolombia.service.verifyaccountowner.model.client.ClientJsonApiResponse;
import co.com.bancolombia.service.verifyaccountowner.processors.ProcessorChannelService;

@Component
public class verifyAccountTransactionRoute extends RouteBuilder{
	
	private Map<String, Object> attributes = new HashMap<>();
	private ProcessorChannelService processorChannelService = new ProcessorChannelService();
	
	public void configure() throws Exception {
        from("direct:verify-account-transaction")
        .process(processorChannelService)
        //Capitulo 4.6
        .log("TransactionRoute Test");        
    }
}
