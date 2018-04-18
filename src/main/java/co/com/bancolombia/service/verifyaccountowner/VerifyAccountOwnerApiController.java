package co.com.bancolombia.service.verifyaccountowner;

import co.com.bancolombia.service.verifyaccountowner.api.VerifyAccountOwnerApi;
import co.com.bancolombia.service.verifyaccountowner.model.JsonApiBody;
import co.com.bancolombia.service.verifyaccountowner.model.VerifyAccountResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupobancolombia.ents.soi.coreextensions.v2.UsernameToken;
import com.grupobancolombia.ents.soi.messageformat.v2.RequestHeader;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v1.ConsultarDetalleExtendido;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v1.IdentificacionCliente;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v1.InformacionCuenta;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v1.ObjectFactory;
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

    @EndpointInject(uri="direct:soap-deposit-account-query")
    private FluentProducerTemplate producerTemplate;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public VerifyAccountOwnerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<VerifyAccountResponse> verifyAccountOwner(@ApiParam(value = "" ,required=true )  @Valid @RequestBody JsonApiBody body) {

        ConsultarDetalleExtendido consultarDetalleExtendido =new ConsultarDetalleExtendido();
        IdentificacionCliente identificacionCliente =new IdentificacionCliente();
        InformacionCuenta informacionCuenta= new InformacionCuenta();

        informacionCuenta.setNumeroCuenta("5555555555");
        informacionCuenta.setTipoCuenta("CD");

        consultarDetalleExtendido.setIdentificacionCliente(identificacionCliente);
        consultarDetalleExtendido.setInformacionCuenta(informacionCuenta);




        Object obj = producerTemplate.withBody(consultarDetalleExtendido).request();

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
