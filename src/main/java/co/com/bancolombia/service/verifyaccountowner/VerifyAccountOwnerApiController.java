package co.com.bancolombia.service.verifyaccountowner;

import co.com.bancolombia.service.verifyaccountowner.api.VerifyAccountOwnerApi;
import co.com.bancolombia.service.verifyaccountowner.model.JsonApiBody;
import co.com.bancolombia.service.verifyaccountowner.model.VerifyAccountRequest;
import co.com.bancolombia.service.verifyaccountowner.model.VerifyAccountResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v2.*;
import io.swagger.annotations.*;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.component.spring.ws.SpringWebserviceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class VerifyAccountOwnerApiController implements VerifyAccountOwnerApi {

    //@EndpointInject(uri="direct:soap-deposit-account-query")
	@EndpointInject(uri="direct:validate-channel-service")
    private FluentProducerTemplate producerTemplate;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public VerifyAccountOwnerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<VerifyAccountResponse> verifyAccountOwner(@ApiParam(value = "" ,required=true )  @Valid @RequestBody JsonApiBody body) {

       /* ConsultarInformacionExtendidaCuenta consultarInformacionExtendidaCuenta = new ConsultarInformacionExtendidaCuenta();
        InformacionCuentaCIE cie = new InformacionCuentaCIE();

        CondicionesComercialesCIE condicionesComercialesCIE = new CondicionesComercialesCIE();
        InformacionTransaccion informacionTransaccion = new InformacionTransaccion();
        informacionTransaccion.setNumeroCuenta("1234567");
        informacionTransaccion.setTipoCuenta("ahorros");
        condicionesComercialesCIE.setInformacionTransaccion(informacionTransaccion);

        InformacionClienteCIE informacionClienteCIE = new InformacionClienteCIE();
        IdentificacionCliente identificacionCliente = new IdentificacionCliente();
        identificacionCliente.setTipoIdentificacion("CC");
        identificacionCliente.setNumeroIdentificacion("11011100000");
        informacionClienteCIE.setIdentificacionCliente(identificacionCliente);


        cie.setCondicionesComerciales(condicionesComercialesCIE);
        cie.setInformacionCliente(informacionClienteCIE);

        consultarInformacionExtendidaCuenta.setInformacionCuenta(cie);
        Object obj = producerTemplate.withBody(consultarInformacionExtendidaCuenta).request();*/

    	
    	VerifyAccountRequest verifyAccountRequest = new VerifyAccountRequest();
    	verifyAccountRequest = body.getData().get(0);

    	
    	Object obj = producerTemplate.withBody(verifyAccountRequest).withHeader("TEMPLATE", "templates/ChannelServiceRq.ftl").withHeader("ENDPOINT", "http://localhost:8081/test/ValidateChannel").request();
        System.out.println(obj);
    	
    	
        /**
         *
         *         String accept = request.getHeader("Accept");
         if (accept != null && accept.contains("application/json")) {
         try {
         return new ResponseEntity<VerifyAccountResponse>(objectMapper.readValue("{  \"header\" : {    \"id\" : \"id\",    \"type\" : \"type\"  },  \"messageId\" : \"messageId\",  \"ownerAccountName\" : \"ownerAccountName\",  \"transactionDate\" : \"transactionDate\"}", VerifyAccountResponse.class), HttpStatus.NOT_IMPLEMENTED);
         } catch (IOException e) {

         return new ResponseEntity<VerifyAccountResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
         }
         */

        return new ResponseEntity<VerifyAccountResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
