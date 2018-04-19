package co.com.bancolombia.service.verifyaccountowner.routes.services;

import com.grupobancolombia.ents.soi.coreextensions.v2.UsernameToken;
import com.grupobancolombia.ents.soi.messageformat.v2.RequestHeader;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.spring.ws.SpringWebserviceConstants;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Override
    public void configure() throws Exception {
        from("direct:soap-deposit-account-query")
                .to("freemarker:templates/DepositAccountQuerySoapRq.ftl")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        RequestHeader requestHeader =new RequestHeader();
                        requestHeader.setSystemId("899988");
                        requestHeader.setMessageId("222333");
                        UsernameToken token = new UsernameToken();
                        token.setUserName("jfescobar");
                        token.setUserToken("6655");
                        requestHeader.setUserId(token);

                        exchange.getIn().setHeader(SpringWebserviceConstants.SPRING_WS_SOAP_HEADER,requestHeader);
                    }
                })
                .to("spring-ws:"+path+"?webServiceTemplate=#webServiceTemplate&soapAction="+soapAction)
                .log("${body}")
                .unmarshal(jaxbDataFormat);
    }
}

