package co.com.bancolombia.service.verifyaccountowner.routes.deposit.account.query;

import com.grupobancolombia.ents.soi.coreextensions.v2.UsernameToken;
import com.grupobancolombia.ents.soi.messageformat.v2.RequestHeader;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v1.ConsultarDetalleExtendido;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.spring.ws.SpringWebserviceConstants;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.xml.transform.StringResult;

import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;

@Component
public class DepositAccountQueryRoute extends RouteBuilder {

    @Autowired
    private JaxbDataFormat jaxbDataFormat;

    @Autowired
    private Jaxb2Marshaller jaxb2Marshaller;

    @Override
    public void configure() throws Exception {
        from("direct:soap-deposit-account-query")
                .log("${body}")
                .to("freemarker:templates/DepositAccountQuerySoapRq.ftl")
                .log("${body}");
                //.marshal(jaxbDataFormat)
               // .to("spring-ws:http://localhost:8088/mockConsultaCuentaDepositosHttpBinding?webServiceTemplate=#webServiceTemplate&soapAction=http://grupobancolombia.com/intf/Producto/Depositos/ConsultaCuentaDepositos/V1.0/consultarDetalleExtendido")
                //.log("${body}")
                //.unmarshal(jaxbDataFormat);

        }
}
