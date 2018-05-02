package co.com.bancolombia.service.verifyaccountowner.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.spring.ws.SpringWebserviceConstants;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.grupobancolombia.ents.soi.coreextensions.v2.Destination;
import com.grupobancolombia.ents.soi.coreextensions.v2.UsernameToken;
import com.grupobancolombia.ents.soi.messageformat.v2.RequestHeader;

import java.util.Map;

/**
 * DepositAccountQueryRoute is a Camel-Route created to invoke the SOAP Service: deposit-account-query.
 * This Route use the Camel Spring-ws component described below: https://github.com/apache/camel/blob/master/components/camel-spring-ws/src/main/docs/spring-ws-component.adoc
 * @author Juan Fernando Escobar Bernal
 * @version 1.0
 */
@Component
public class DepositAccountQueryRoute extends RouteBuilder {

    @Autowired
    private JaxbDataFormat jaxbDataFormat;

    @Value("${soap.deposit.account.query.path}")
    private String path;

    @Value("${soap.deposit.account.query.soap.action}")
    private String soapAction;

    @Value("${soap.deposit.account.query.freemarker.template}")
    private String freemarkerTemplate;

    @Value("${soap.deposit.account.query.system.id}")
    private String systemId;

    @Value("${soap.deposit.account.query.user.name}")
    private String userName;

    @Value("${soap.deposit.account.query.name.space}")
    private String nameSpace;

    public static final String ROUTE_ID ="soap-deposit-account-query";
	private final String ERROR = "Error";
	private final String DESC_ERROR = "desc-error";

    @Override
    public void configure() throws Exception {
        from("direct:soap-deposit-account-query")
                .routeId(ROUTE_ID)
                .validate(body().isInstanceOf(Map.class))
                .to("freemarker:"+freemarkerTemplate)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        RequestHeader requestHeader =new RequestHeader();
                        requestHeader.setSystemId(systemId);
                        requestHeader.setMessageId("9900000000000095");
                        //*********PONER TIMESTAMP
                        UsernameToken token = new UsernameToken();
                        token.setUserName(userName);
                        //token.setUserToken("6655");
                        Destination dest = new Destination();
                        dest.setName("ConsultaCuentaDepositos");
                        dest.setNamespace(nameSpace);
                        dest.setOperation("consultarInformacionExtendidaCuenta");
                        requestHeader.setDestination(dest);
                        requestHeader.setUserId(token);

                        exchange.getIn().setHeader(SpringWebserviceConstants.SPRING_WS_SOAP_HEADER,requestHeader);
                    }
                })
                
                .hystrix()
        	    .hystrixConfiguration().executionTimeoutInMilliseconds(2000).end()
	        	    .to("spring-ws:"+path+"?webServiceTemplate=#webServiceTemplate&soapAction="+soapAction)
	                .log("Request SOAP Deposit Account ${body}")
	                .unmarshal(jaxbDataFormat)
	        		.setHeader(this.ERROR, constant("0000"))
	        		.setHeader(this.DESC_ERROR, constant("No error"))
	        	.endHystrix()
	        	    .onFallback()
	   			 // we use a fallback without network that provides a response message immediately
	   			 //.transform().simple("Fallback ${body}")
		       		.setHeader(this.ERROR, constant("0004"))
		       		.setHeader(this.DESC_ERROR, constant("Error invocando el servicio /deposit-account-dp"))
	       		.end();
    }
}

