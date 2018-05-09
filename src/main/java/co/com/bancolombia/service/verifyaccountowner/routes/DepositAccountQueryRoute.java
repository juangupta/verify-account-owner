package co.com.bancolombia.service.verifyaccountowner.routes;

import java.util.Map;

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
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v2.ConsultarInformacionExtendidaCuentaResponse;

import co.com.bancolombia.service.verifyaccountowner.model.client.ClientJsonApiResponse;

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
	private final String DESC_ERROR = "descError";
	
	private Map<String, Object> attributes;

    @Override
    public void configure() throws Exception {
        from("direct:soap-deposit-account-query")
		 .process(new Processor() {
					
					@Override
					public void process(Exchange exchange) throws Exception {
						// TODO Auto-generated method stub
						attributes = (Map<String, Object>) exchange.getIn().getBody();				
					}
				})
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
	        		.process(new Processor(){        	
	        			@Override
	    				public void process(Exchange exchange) throws Exception {
	    					
	        				ConsultarInformacionExtendidaCuentaResponse depositAccountServiceResponse = (ConsultarInformacionExtendidaCuentaResponse) exchange.getIn().getBody();
	        				attributes.put("RelacionCliente", depositAccountServiceResponse.getInformacionCuenta().getInformacionCliente().getRelacionCliente().getRelacionClienteCuenta());
	        				attributes.put("NombreTitular", depositAccountServiceResponse.getInformacionCuenta().getInformacionCliente().getIdentificacionTitular().getNombreTitular());
	        				attributes.put("NumeroIdentificacion", depositAccountServiceResponse.getInformacionCuenta().getInformacionCliente().getIdentificacionTitular().getNumeroIdentificacion());
	        				attributes.put("TipoIdentificacion", depositAccountServiceResponse.getInformacionCuenta().getInformacionCliente().getIdentificacionTitular().getTipoIdentificacion());					
	        				attributes.put("NumeroCuenta", depositAccountServiceResponse.getInformacionCuenta().getCondicionesComerciales().getInformacionTransaccion().getNumeroCuenta());
	        				attributes.put("TipoCuenta", depositAccountServiceResponse.getInformacionCuenta().getCondicionesComerciales().getInformacionTransaccion().getTipoCuenta());
	        				if (attributes.get("RelacionCliente").equals("T") && 
	        					attributes.get("NumeroIdentificacion").equals(attributes.get("BeneficiaryDocument")) &&
	        					attributes.get("TipoIdentificacion").equals(attributes.get("BeneficiaryDocumentType")) &&
	        					attributes.get("NumeroCuenta").equals(attributes.get("ProductNumber")) &&
	        					attributes.get("TipoCuenta").equals(attributes.get("ProductType"))) 
	        				{
	        					exchange.getIn().setHeader(ERROR, "0000");
	        					exchange.getIn().setHeader(DESC_ERROR, "No error");
	        				}
	        				else 
	        				{
	        					exchange.getIn().setHeader(ERROR, "0002");
	        					exchange.getIn().setHeader(DESC_ERROR, "Cliente no es titular de la cuenta");
	        				}
	        				exchange.getIn().setBody(attributes);
	        			}
	        				
	        		})
	        	.endHystrix()
	        	    .onFallback()
	   			 // we use a fallback without network that provides a response message immediately
	   			 //.transform().simple("Fallback ${body}")
		       		.setHeader(this.ERROR, constant("0004"))
		       		.setHeader(this.DESC_ERROR, constant("Error invocando el servicio /deposit-account-dp"))
	       		.end();
    }
}

